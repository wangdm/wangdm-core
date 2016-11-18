package com.wangdm.core.dao;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.constraint.Condition;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.entity.Entity;

public interface Dao<E extends Entity> {

    public Class<E> getClazz();

    public void setClazz(Class<E> clazz);
    
    public Serializable create(E entity);
    
    public void update(E entity);
    
    public void delete(E entity);
    
    public void deleteById(Serializable id);
    
    public void deleteById(Class<?> clazz, Serializable id);
    
    public void deleteByColumn(String column, Object value);
    
    public E findById(Serializable id);
    
    public E findById(Class<?> clazz, Serializable id);
    
    public List<E> listAll();
    
    public List<E> findByColumn(String column, Object value);
    
    public List<E> findByCondition(Condition condition);
    
    public List<E> findByConstraint(Constraint constraint);
}
