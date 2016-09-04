package com.wangdm.user.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;
import com.wangdm.user.constant.RoleType;

@Entity
@Table(name="role")
public class Role extends BaseEntity{
    
    private static final long serialVersionUID = 6650065305311471444L;
    
    public static final EntityType entityType = EntityType.ROLE;
    
    @Column(name="name", nullable=false, unique=true, length=20)
    private String name;
    
    @Column(name="title", nullable=false, length=20)
    private String title;
    
    @Column(name="type", nullable=false)
    private RoleType type = RoleType.USER;
    
    @Column(name="description", length=200)
    private String desc;

    @OneToMany(targetEntity=RolePermission.class, mappedBy="permission")
    private Set<RolePermission> permissions;
    
    @OneToMany(targetEntity=UserRole.class, mappedBy="user")
    private Set<UserRole> user;
    
    @OneToMany(targetEntity=GroupRole.class, mappedBy="group")
    private Set<GroupRole> group;
    
    public boolean hasPermission(Permission perm, int action){
    	for(RolePermission permission : permissions){
    		if(permission.getPermission().getId()==perm.getId()){
    			if( (permission.getAction() & action) == action ){
    				return true;
    			}
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RoleType getType() {
		return type;
	}

	public void setType(RoleType type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Set<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<RolePermission> permissions) {
		this.permissions = permissions;
	}

	public Set<UserRole> getUser() {
		return user;
	}

	public void setUser(Set<UserRole> user) {
		this.user = user;
	}

	public Set<GroupRole> getGroup() {
		return group;
	}

	public void setGroup(Set<GroupRole> group) {
		this.group = group;
	}
    
}
