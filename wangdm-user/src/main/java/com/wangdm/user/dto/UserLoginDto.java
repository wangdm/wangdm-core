package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.User;

public class UserLoginDto extends BaseDto {
    
    @DtoMapper(entity =User.class, field = "id")
    private int id;
    
    @DtoMapper(entity=User.class, field="username")
	private String loginname;
	
    @DtoMapper(entity=User.class, field="password")
	private String loginpwd;
	
    @DtoMapper(entity=User.class, field="email")
	private String email;

    @DtoMapper(entity=User.class, field="phone")
    private String phone;
    
    
    @Override
    public Long getEntityId() {
        return Long.valueOf(id);
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getLoginname() {
        return loginname;
    }


    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    public String getLoginpwd() {
        return loginpwd;
    }


    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
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
}
