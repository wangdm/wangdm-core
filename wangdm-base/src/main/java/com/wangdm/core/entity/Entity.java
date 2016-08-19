package com.wangdm.core.entity;

import java.io.Serializable;

import com.wangdm.core.constant.EntityStatus;

public interface Entity extends Serializable {
    
    public Long getId();
    
    public void setId(Long id);
    
    public EntityStatus getStatus();
    
    public void setStatus(EntityStatus status);

}
