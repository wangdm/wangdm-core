package com.wangdm.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="user_group")
public class UserGroup extends BaseEntity {

	private static final long serialVersionUID = -5087226337261443829L;
	
    @OneToOne
    @JoinColumn(name="userId", referencedColumnName="id", nullable=false)
	private User user;

    @ManyToOne
    @JoinColumn(name="groupId", referencedColumnName="id", nullable=false)
	private Group group;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
