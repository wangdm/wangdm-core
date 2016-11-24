package com.wangdm.user.dto;

import java.util.List;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.User;
import com.wangdm.user.entity.UserProfile;

public class UserDto extends BaseDto {
	
	@DtoMapper(entity=User.class, field="id")
	private String id;

	@DtoMapper(entity=User.class, field="username")
	private String username;

	@DtoMapper(entity=User.class, field="email")
	private String email;

	@DtoMapper(entity=User.class, field="phone")
	private String phone;

    @DtoMapper(entity=UserProfile.class, field="nickname")
    private String nickname;

    @DtoMapper(entity=UserProfile.class, field="realname")
    private String realname;

    @DtoMapper(entity=UserProfile.class, field="avatar")
    private String avatar;

    @DtoMapper(entity=UserProfile.class, field="gender")
    private String gender;

    @DtoMapper(entity=UserProfile.class, field="age")
    private String age;

    @DtoMapper(entity=UserProfile.class, field="motto")
    private String motto;

	@DtoMapper(entity=User.class, field="group.id")
	private String gid;

	@DtoMapper(entity=User.class, field="group.name")
	private String groupname;
	
    private String rolename;

    @DtoMapper(entity=User.class, field="status")
	private String status;
    
    private List<RoleDto> role;

    @Override
    public Long getEntityId() {
        return Long.valueOf(id);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<RoleDto> getRole() {
        return role;
    }

    public void setRole(List<RoleDto> role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
