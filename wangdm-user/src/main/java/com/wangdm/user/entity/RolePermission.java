package com.wangdm.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="role_permission")
public class RolePermission extends BaseEntity {

    private static final long serialVersionUID = -826410274186829993L;
    
    public static final EntityType entityType = EntityType.ROLEPERMISSION;
    
    @ManyToOne
    @JoinColumn(name="roleId", nullable=false)
    private Role role;

    @ManyToOne
    @JoinColumn(name="permissionId", nullable=false)
    private Permission permission;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
