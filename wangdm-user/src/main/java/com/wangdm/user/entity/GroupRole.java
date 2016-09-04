package com.wangdm.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="group_role")
public class GroupRole extends BaseEntity{

    private static final long serialVersionUID = 8237756279531412796L;

    public static final EntityType entityType = EntityType.GROUPROLE;
    
    @ManyToOne
    @JoinColumn(name="groupId", referencedColumnName="id", nullable=false)
    private Group group;

    @ManyToOne
    @JoinColumn(name="roleId", referencedColumnName="id", nullable=false)
    private Role role;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static EntityType getEntitytype() {
		return entityType;
	}
}
