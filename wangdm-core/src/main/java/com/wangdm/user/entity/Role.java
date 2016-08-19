package com.wangdm.user.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;
import com.wangdm.user.constant.RoleType;

@Entity
@Table(name="role")
public class Role extends BaseEntity{
    
    private static final long serialVersionUID = 6650065305311471444L;
    
    public static final EntityType entityType = EntityType.ROLE;
    
    @Column(name="name", nullable=false, length=20)
    private String name;
    
    @Column(name="type", nullable=false)
    private RoleType type = RoleType.USER;
    
    @Column(name="description", length=200)
    private String desc;

    @OneToMany(targetEntity=RolePermission.class, mappedBy="permission")
    private Set<RolePermission> permission;
    
    @OneToMany(targetEntity=UserRole.class, mappedBy="user")
    private Set<UserRole> user;
    
    @OneToMany(targetEntity=GroupRole.class, mappedBy="group")
    private Set<GroupRole> group;
    
}
