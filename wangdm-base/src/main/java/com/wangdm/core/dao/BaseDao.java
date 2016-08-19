package com.wangdm.core.dao;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.query.Condition;
import com.wangdm.core.query.Page;

public interface BaseDao<T> {
    
    public Serializable create(T entity);
    
    public void update(T entity);
    
    public void delete(Serializable id);
    
    public T findById(Serializable id);
    
    public List<T> listAll();
    
    public List<T> listAll(Page page);
    
    public List<T> listAll(String order, Page page);
    
    public List<T> findByColumn(String column, Serializable id);
    
    public List<T> findByColumn(String column, Serializable id, Page page);
    
    public List<T> findByCondition(Condition condition, Page page);
    
    public Long countByColumn(String column, Serializable id);
    
    public Long countByCondition(Condition condition);
}
