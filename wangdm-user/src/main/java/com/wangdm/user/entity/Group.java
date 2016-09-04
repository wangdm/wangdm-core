package com.wangdm.user.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="usergroup")
public class Group extends BaseEntity {

    private static final long serialVersionUID = -6051400523431369139L;
    
    public static final EntityType entityType = EntityType.GROUP;
    
    @Column(name="name", nullable=false, length=20)
    private String name;
    
    @Column(name="description", length=200)
    private String desc;
    
    @ManyToOne
    @JoinColumn(name="parentId", referencedColumnName="id")
    private Group parent;
    
    @OneToMany(targetEntity=Group.class, mappedBy="parent")
    private List<Group> children;

    @OneToMany(targetEntity=GroupRole.class, mappedBy="role")
    private Set<GroupRole> roles;
    
    public boolean hasPermission(Permission perm, int action){
    	for(GroupRole groupRole : roles){
    		Role role = groupRole.getRole();
    		if(role.hasPermission(perm, action)){
    			return true;
    		}
    	}
    	return false;
    }

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

	public Group getParent() {
		return parent;
	}

	public void setParent(Group parent) {
		this.parent = parent;
	}

	public List<Group> getChildren() {
		return children;
	}

	public void setChildren(List<Group> children) {
		this.children = children;
	}

	public Set<GroupRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<GroupRole> roles) {
		this.roles = roles;
	}

}
