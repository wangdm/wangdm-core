package com.wangdm.core.service;

import java.io.Serializable;

import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;

public interface Service {
    
    public Serializable create(Dto dto);
    
    public void update(Dto dto);
    
    public void delete(Serializable id);
    
    public void erase(Serializable id);
    
    public void restore(Serializable id);
    
    public Dto findById(Serializable id);
    
    public QueryResult query(Query query);
}
