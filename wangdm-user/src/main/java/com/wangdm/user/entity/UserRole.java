package com.wangdm.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity{

    private static final long serialVersionUID = 8082325465591780032L;
    
    public static final EntityType entityType = EntityType.USERROLE;
    
    @ManyToOne(targetEntity=User.class)
    @JoinColumn(name="userId", referencedColumnName="id", nullable=false)
    private User user;

    @ManyToOne(targetEntity=Role.class)
    @JoinColumn(name="roleId", referencedColumnName="id", nullable=false)
    private Role role;
    
    public UserRole(){
        this.setStatus(EntityStatus.NORMAL);
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
