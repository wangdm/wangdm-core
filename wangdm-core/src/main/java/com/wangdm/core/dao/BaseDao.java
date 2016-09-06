package com.wangdm.core.dao;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.constraint.Condition;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.Page;
import com.wangdm.core.entity.Entity;

public interface BaseDao<E extends Entity> {
    
    public Serializable create(E entity);
    
    public void update(E entity);
    
    public void delete(Serializable id);
    
    public E findById(Serializable id);
    
    public List<E> listAll();
    
    public List<E> listAll(Page page);
    
    public List<E> listAll(String order, Page page);
    
    public List<E> findByColumn(String column, Serializable id);
    
    public List<E> findByColumn(String column, Serializable id, Page page);
    
    public List<E> findByConstraint(Constraint constraint);
    
    public List<E> findByCondition(Condition condition, Page page);
    
    public Long countByColumn(String column, Serializable id);
    
    public Long countByCondition(Condition condition);
}
