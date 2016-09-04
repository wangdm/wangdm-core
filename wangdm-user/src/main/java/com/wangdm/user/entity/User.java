package com.wangdm.user.entity;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.BaseEntity;

@Entity
@Table(name="user")
public class User extends BaseEntity {
    
    private static final long serialVersionUID = -2373734406726264823L;
    
    public static final EntityType entityType = EntityType.USER;
    
    @Column(name="username", nullable=false, unique=true, length=40)
    private String username;

    @Column(name="password", nullable=false, length=40)
    private String password;

    @Column(name="phone", unique=true)
    private String phone;
    
    @Column(name="email", unique=true)
    private String email;
    
    @OneToMany(targetEntity=UserRole.class, mappedBy="role")
    private Set<UserRole> roles;
    
    public boolean hasPermission(Permission perm, int action){
    	for(UserRole userRole : roles){
    		Role role = userRole.getRole();
    		if(role.hasPermission(perm, action)){
    			return true;
    		}
    	}
    	return false;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
}
