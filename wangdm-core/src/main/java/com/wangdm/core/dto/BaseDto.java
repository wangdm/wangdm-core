package com.wangdm.core.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.core.entity.Entity;
import com.wangdm.core.entity.Pojo;

public abstract class BaseDto extends Pojo implements Dto {

    public static final Logger log = LoggerFactory.getLogger(BaseDto.class);

    @Override
    public Entity toEntity(Class<?> clazz) {
        String className = clazz.getName();
        log.debug("Get "+className+" from "+this.getClass().getName());
        
        // Construct entity
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
        return this.toEntity(entity);
    }

    @Override
    public Entity toEntity(Entity entity) {
        
        if(entity==null){
            return null;
        }
        
        Class<?> clazz = entity.getClass();
        String className = clazz.getName();
        log.debug("Get "+className+" from "+this.getClass().getName());
        
        Field[] dtoFields = this.getClass().getDeclaredFields();
        for(Field dtoField : dtoFields){
            if(dtoField.isAnnotationPresent(DtoMapper.class)){
                DtoMapper mapper = dtoField.getAnnotation(DtoMapper.class);
                if(clazz==mapper.entity()){
                    try {
                        String mapperFieldName = mapper.field();
                        String mapperFields[] = mapperFieldName.split("\\.");
                        if(mapperFields.length==1){
                            copyValueToObject(entity, getClassField(clazz,mapperFieldName), dtoField);
                        }else if(mapperFields.length==2){
                            Field entityField = getClassField(clazz, mapperFields[0]);
                            Class<?> innerClazz = entityField.getType();
                            Object obj = null;
                            entityField.setAccessible(true);
                            obj = entityField.get(entity);
                            if(obj==null){
                                obj = innerClazz.newInstance();
                                obj = copyValueToObject(obj, getClassField(innerClazz, mapperFields[1]), dtoField);
                                entityField.set(entity, obj);
                            }else{
                                copyValueToObject(obj, getClassField(innerClazz, mapperFields[1]), dtoField);
                            }
                        }
                        
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                        return null;
                    }catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        log.error(className + " hasn't field " + mapper.field());
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
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
        
        if(entity==null){
            return;
        }
        
        Class<?> entityClazz = entity.getClass();
        String className = entityClazz.getName();
        log.debug("Init "+this.getClass().getName()+" from "+className);
        
        Field[] dtoFields = this.getClass().getDeclaredFields();
        for(Field dtoField : dtoFields){
            if(dtoField.isAnnotationPresent(DtoMapper.class)){
                DtoMapper mapper = dtoField.getAnnotation(DtoMapper.class);
                if(entityClazz == mapper.entity()){
                    try {
                        String mapperFieldName = mapper.field();
                        String mapperFields[] = mapperFieldName.split("\\.");
                        Field entityField = getClassField(entityClazz, mapperFields[0]);
                        if(mapperFields.length==1){
                            copyValueFromObject(entity, entityField, dtoField);
                        }else if(mapperFields.length==2){
                            Class<?> innerClazz = entityField.getType();
                            entityField.setAccessible(true);
                            Object obj = entityField.get(entity);
                            if(obj!=null){
                            	copyValueFromObject(obj, getClassField(innerClazz, mapperFields[1]), dtoField);
                            }
                        }
                        
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        log.error(className + " hasn't field " + mapper.field());
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
    
    private Object copyValueToObject(Object obj, Field objField, Field field) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{

        Class<?> objFieldType = objField.getType();
        
        field.setAccessible(true);
        objField.setAccessible(true);
        Object value = field.get(this);
        if(value==null){
            return null;
        }
        
        if(objFieldType==field.getType()){
            objField.set(obj, value);
        }
        else if(objFieldType.getName().equals("java.lang.String")){
            objField.set(obj, value.toString());
        }else if(objFieldType.getName().equals("java.lang.Long")){
            objField.set(obj, Long.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Integer")){
            objField.set(obj, Integer.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Short")){
            objField.set(obj, Short.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Byte")){
            objField.set(obj, Byte.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Float")){
            objField.set(obj, Float.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Double")){
            objField.set(obj, Double.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.lang.Boolean")){
            objField.set(obj, Boolean.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.sql.Timestamp")){
            objField.set(obj, Timestamp.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.sql.Date")){
            objField.set(obj, Date.valueOf(value.toString()));
        }
        else if(objFieldType.getName().equals("java.sql.Time")){
            objField.set(obj, Time.valueOf(value.toString()));
        }
        else if(objFieldType.isEnum()){
            try{
                int i = Integer.parseInt((String)value);
                Method method = objFieldType.getMethod("valueOf", int.class);
                objField.set(obj, method.invoke(null, i));
            }catch(NumberFormatException e){
                Method method = objFieldType.getMethod("valueOf", String.class);
                objField.set(obj, method.invoke(null, value));
            }
        }
        else if(objFieldType.isPrimitive()){
            if(objFieldType.getName().equals("int")){
                objField.setInt(obj, Integer.parseInt(value.toString()));
            }
            else if(objFieldType.getName().equals("long")){
                objField.setLong(obj, Long.parseLong(value.toString()));
            }
            else if(objFieldType.getName().equals("short")){
                objField.setShort(obj, Short.parseShort(value.toString()));
            }
            else if(objFieldType.getName().equals("byte")){
                objField.setByte(obj, Byte.parseByte(value.toString()));
            }
            else if(objFieldType.getName().equals("float")){
                objField.setFloat(obj, Float.parseFloat(value.toString()));
            }
            else if(objFieldType.getName().equals("double")){
                objField.setDouble(obj, Double.parseDouble(value.toString()));
            }
            else if(objFieldType.getName().equals("boolean")){
                objField.setBoolean(obj, Boolean.parseBoolean(value.toString()));
            }
            else if(objFieldType.getName().equals("char")){
                objField.setChar(obj, value.toString().charAt(0));
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        return obj;
    }
    
    private void copyValueFromObject(Object obj, Field objField, Field field) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{

        Class<?> fieldType = field.getType();
        field.setAccessible(true);
        objField.setAccessible(true);
        Object value = objField.get(obj);
        if(value==null){
            return;
        }
        
        if(fieldType==objField.getType()){
            field.set(this, value);
        }else if(fieldType.getName().equals("java.lang.String")){
            field.set(this, value.toString());
        }else if(fieldType.getName().equals("java.lang.Long")){
            field.set(this, Long.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Integer")){
            field.set(this, Integer.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Short")){
            field.set(this, Short.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Byte")){
            field.set(this, Byte.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Float")){
            field.set(this, Float.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Double")){
            field.set(this, Double.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.lang.Boolean")){
            field.set(this, Boolean.valueOf((String) value));
        }
        else if(fieldType.getName().equals("java.sql.Timestamp")){
            field.set(this, Timestamp.valueOf(value.toString()));
        }
        else if(fieldType.getName().equals("java.sql.Date")){
            field.set(this, Date.valueOf(value.toString()));
        }
        else if(fieldType.getName().equals("java.sql.Time")){
            field.set(this, Time.valueOf(value.toString()));
        }
        else if(fieldType.isPrimitive()){
            if(fieldType.getName().equals("int")){
                field.setInt(this, Integer.parseInt(value.toString()));
            }
            else if(fieldType.getName().equals("long")){
                field.setLong(this, Long.parseLong(value.toString()));
            }
            else if(fieldType.getName().equals("short")){
                field.setShort(this, Short.parseShort(value.toString()));
            }
            else if(fieldType.getName().equals("byte")){
                field.setByte(this, Byte.parseByte(value.toString()));
            }
            else if(fieldType.getName().equals("float")){
                field.setFloat(this, Float.parseFloat(value.toString()));
            }
            else if(fieldType.getName().equals("double")){
                field.setDouble(this, Double.parseDouble(value.toString()));
            }
            else if(fieldType.getName().equals("boolean")){
                field.setBoolean(this, Boolean.parseBoolean(value.toString()));
            }
            else if(fieldType.getName().equals("char")){
                field.setChar(this, value.toString().charAt(0));
            }
        }
        else{
            throw new IllegalArgumentException(fieldType.getName());
        }
    }
    
    private Field getClassField(Class<?> clazz, String name) throws NoSuchFieldException{
        Field field = null; 
        Class<?> superClazz = null;
        do{
            try {
                if(superClazz == null){
                    field = clazz.getDeclaredField(name);
                } else{
                    field = superClazz.getDeclaredField(name);
                }
                break;
            } catch (NoSuchFieldException | SecurityException e) {
                if(superClazz == null){
                    superClazz = clazz.getSuperclass();
                } else{
                    superClazz = superClazz.getSuperclass();
                }
                if(superClazz.getName().equals("java.lang.Object")){
                    throw new NoSuchFieldException(name);
                }
            }
            
        }while(true);
        
        return field;
    }

}
