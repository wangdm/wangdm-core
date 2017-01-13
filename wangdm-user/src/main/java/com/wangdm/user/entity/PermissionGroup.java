package com.wangdm.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="permissiongroup")
public class PermissionGroup extends BaseEntity {

    private static final long serialVersionUID = 1285169705446811156L;
    
    @Column(name="name", nullable=false, length=20)
    private String name;
    
    @Column(name="description", length=200)
    private String desc;
    
    @ManyToOne
    @JoinColumn(name="parentId", referencedColumnName="id")
    private PermissionGroup parent;
    
    @OneToMany(targetEntity=Group.class, mappedBy="parent")
    private List<PermissionGroup> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PermissionGroup getParent() {
        return parent;
    }

    public void setParent(PermissionGroup parent) {
        this.parent = parent;
    }

    public List<PermissionGroup> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionGroup> children) {
        this.children = children;
    }

    @Override
    public String toString() {

        return "PermissionGroup [name=" + name + ", desc=" + desc + "]";
    }
    
}
