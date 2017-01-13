package com.wangdm.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wangdm.core.constant.EntityType;
import com.wangdm.core.entity.StatusEntity;

@Entity
@Table(name = "user")
public class User extends StatusEntity {

	private static final long serialVersionUID = -2373734406726264823L;

	public static final EntityType entityType = EntityType.USER;

	@Column(name = "username", nullable = false, unique = true, length = 40)
	private String username;

	@Column(name = "password", nullable = false, length = 40)
	private String password;

	@Column(name = "phone", unique = true, length = 40)
	private String phone;

	@Column(name = "email", unique = true, length = 40)
	private String email;

	@ManyToMany(targetEntity = UserRole.class, mappedBy = "role")
	private List<Role> roles;

	@ManyToOne(targetEntity = Group.class, optional = true)
    @JoinColumn(name="groupId", referencedColumnName="id")
	private Group group;

	public boolean hasPermission(Permission perm, int action) {
		for (Role role : roles) {
			if (role.hasPermission(perm, action)) {
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
