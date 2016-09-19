package com.wangdm.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.entity.Entity;

@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseService<E extends Entity> implements Service {
    
    private static final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private Dao<E> baseDao;
    
    private Class<E> clazz;
    
    public BaseService(){
        String fullClassName = this.getClass().getName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        if(!"BaseService".equals(className))
        {
            //System.out.println("this class name is "+ className);
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
            clazz = (Class<E>) type.getActualTypeArguments()[0];
        }
        
    }
    
    @Override
    public Serializable create(Dto dto) {
        
        E entity = (E) dto.toEntity(clazz);
        
        return baseDao.create(entity);
    }

    @Override
    public void update(Dto dto) {
        
        Long id = dto.getEntityId();
        if(id==null || id.longValue()<=0){
            log.warn(dto.getClass().getName()+" must reimplement getEntityId()");
            return;
         }
        E entity = baseDao.findById(clazz, id);
        if(entity!=null){
            dto.toEntity(entity);
            baseDao.update(entity);
        }
    }

    @Override
    public void delete(Serializable id) {
        
        E entity = baseDao.findById(clazz, id);
        if( entity != null ){
            entity.setStatus(EntityStatus.DELETE);
            baseDao.update(entity);
        }
        
    }

    @Override
    public void erase(Serializable id) {
        
        baseDao.delete(clazz, id);
        
    }

    @Override
    public void restore(Serializable id) {
        
        E entity = baseDao.findById(clazz, id);
        if( entity != null ){
            entity.setStatus(EntityStatus.NORMAL);
            baseDao.update(entity);
        }
        
    }

}
