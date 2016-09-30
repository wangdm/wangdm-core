package com.wangdm.core.service;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;

public interface Service {
    
    public Serializable create(Dto dto);
    
    public void update(Dto dto);
    
    public void delete(Serializable id);
    
    public void erase(Serializable id);
    
    public void restore(Serializable id);
    
    public Dto findById(Serializable id);
    
    public List<Dto> query(Query query);
    
    public List<Dto> listAll(Class<?> dtoClass);
}
