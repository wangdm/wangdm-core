package com.wangdm.core.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.base.entity.BaseEntity;
import com.wangdm.base.constant.EntityType;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity{

    private static final long serialVersionUID = 8082325465591780032L;
    
    public static final EntityType entityType = EntityType.USERROLE;
    
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName="id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="roleId", referencedColumnName="id", nullable=false)
    private Role role;
}
