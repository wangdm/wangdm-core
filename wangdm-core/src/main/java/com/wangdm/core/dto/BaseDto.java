package com.wangdm.core.dto;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.core.entity.Entity;

public abstract class BaseDto implements Dto {
    
    public static final Logger log = LoggerFactory.getLogger(BaseDto.class);

    @Override
    public Entity toEntity(Class<?> clazz) {
        String className = clazz.getName();
        log.debug("Get "+className+" from "+this.getClass().getName());
        Entity entity = null;
        try {
            entity = (Entity) clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
            return null;
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            return null;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(DtoMapper.class)){
                DtoMapper mapper = field.getAnnotation(DtoMapper.class);
                String entityName = mapper.entity();
                if(className.contains(entityName)){
                    Class<?> dtoFieldType = field.getType();
                    try {
                        Field entityField = clazz.getDeclaredField(mapper.field());
                        entityField.setAccessible(true);
                        Class<?> entityFieldType = entityField.getType();
                        try {
                            field.setAccessible(true);
                            if(dtoFieldType==entityFieldType){
                                entityField.set(entity, field.get(this));
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchFieldException e) {
                        log.error(className + " hasn't field " + mapper.field());
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public void fromEntity(Entity entity) {
        log.debug("Init "+this.getClass().getName()+" from "+entity.getClass().getName());

    }

}
