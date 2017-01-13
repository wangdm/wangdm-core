package com.wangdm.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class StatusEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="status", nullable=false)
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
