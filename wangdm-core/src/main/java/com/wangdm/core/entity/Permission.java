package com.wangdm.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wangdm.base.constant.EntityType;
import com.wangdm.base.entity.BaseEntity;

@Entity
@Table(name="permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 3452517882018022853L;
    
    public static final EntityType entityType = EntityType.PERMISSION;

    @Column(name="name", nullable=false, length=20)
    private String name;
    
    @Column(name="description", length=200)
    private String desc;

}
