package com.wangdm.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.entity.ForeignEntity;

@Entity
@Table(name="user_group")
public class UserGroup extends ForeignEntity {

	private static final long serialVersionUID = -5087226337261443829L;

    @Id
    @OneToOne
    @JoinColumn(name="id", referencedColumnName="id", nullable=false)
	private User user;

    @ManyToOne
    @JoinColumn(name="groupId", referencedColumnName="id", nullable=false)
	private Group group;
    
    public UserGroup(){
        this.setStatus(EntityStatus.NORMAL);
    }

    @Override
    public Long getId() {
        user.getId();
        return null;
    }

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
