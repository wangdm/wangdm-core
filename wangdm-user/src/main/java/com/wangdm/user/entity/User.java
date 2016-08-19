package com.wangdm.user.entity;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="user")
public class User extends BaseEntity {
    
    private static final long serialVersionUID = -2373734406726264823L;
    
    public static final EntityType entityType = EntityType.USER;
    
    @Column(name="username", nullable=false, unique=true, length=40)
    private String username;

    @Column(name="password", nullable=false, length=40)
    private String password;

    @Column(name="phone", unique=true)
    private String phone;
    
    @Column(name="email", unique=true)
    private String email;
    
    @OneToMany(targetEntity=UserRole.class, mappedBy="role")
    private Set<UserRole> role;
}
