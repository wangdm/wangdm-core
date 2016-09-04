package com.wangdm.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @JoinColumn(name="parentId", referencedColumnName="id")
    private Permission parent;
    
    @OneToMany(targetEntity=Permission.class, mappedBy="parent")
    private List<Permission> children;

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

	public Permission getParent() {
		return parent;
	}

	public void setParent(Permission parent) {
		this.parent = parent;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}

}
