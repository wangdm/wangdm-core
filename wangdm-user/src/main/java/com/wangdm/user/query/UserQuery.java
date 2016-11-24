package com.wangdm.user.query;

import java.util.List;

import com.wangdm.core.query.BaseQuery;

public class UserQuery extends BaseQuery {
    
    private String username;
    
    private String phone;
    
    private String email;
    
    private Long groupId;
    
    private List<Long> roles;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
    
    public Long getGroupId() {
        return groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public List<Long> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
    
}
