package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.User;

public class UserRegisterDto extends BaseDto {
    
    @DtoMapper(entity =User.class, field = "id")
    private int id;
    
    @DtoMapper(entity=User.class, field="username")
    private String username;
    
    @DtoMapper(entity=User.class, field="password")
    private String password;
    
    private String _password;
    
    @DtoMapper(entity=User.class, field="email")
    private String email;

    @DtoMapper(entity=User.class, field="phone")
    private String phone;
    
    private String role;
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Long getEntityId() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
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
