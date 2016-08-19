package com.wangdm.core.service;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.entity.Entity;

public interface Service<E extends Entity> {
    
    public Serializable create(E entity);
    
    public void update(E entity);
    
    public void delete(Serializable id);
    
    public E findById(Serializable id);
    
    public List<E> listAll();
}
