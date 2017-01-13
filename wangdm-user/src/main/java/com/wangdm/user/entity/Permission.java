package com.wangdm.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 3452517882018022853L;
    
    public static final EntityType entityType = EntityType.PERMISSION;

    @Column(name="name", nullable=false, unique=true, length=20)
    private String name;

    @Column(name="title", nullable=false, length=20)
    private String title;
    
    @Column(name="description", length=200)
    private String desc;
    
    @ManyToOne
    @JoinColumn(name="groupId", referencedColumnName="id")
    private PermissionGroup group;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public PermissionGroup getGroup() {
		return group;
	}

	public void setGroup(PermissionGroup group) {
		this.group = group;
	}

}
