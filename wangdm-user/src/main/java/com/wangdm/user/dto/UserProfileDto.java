package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.UserProfile;

public class UserProfileDto extends BaseDto {
	
	@DtoMapper(entity=UserProfile.class, field="user.id")
	private String id;
	
	@DtoMapper(entity=UserProfile.class, field="nickname")
	private String nickname;
    
    @DtoMapper(entity=UserProfile.class, field="realname")
    private String realname;
	
	@DtoMapper(entity=UserProfile.class, field="avatar")
	private String avatar;
	
	@DtoMapper(entity=UserProfile.class, field="gender")
	private String gender;
	
	@DtoMapper(entity=UserProfile.class, field="age")
	private int age;
	
	@DtoMapper(entity=UserProfile.class, field="motto")
	private String motto;

	@DtoMapper(entity=UserProfile.class, field="user.phone")
	private String phone;
	
	@DtoMapper(entity=UserProfile.class, field="user.email")
	private String email;
	
	@DtoMapper(entity=UserProfile.class, field="user.username")
	private String username;
	
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

	
	public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
