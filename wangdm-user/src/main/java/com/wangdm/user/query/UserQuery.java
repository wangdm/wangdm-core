package com.wangdm.user.query;

import java.util.List;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.query.PageQuery;

public class UserQuery extends PageQuery {
    
    private String name;
    
    private String phone;
    
    private String email;
    
    private EntityStatus status;
    
    private Long group;
    
    private List<Long> roles;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public EntityStatus getStatus() {
        return status;
    }

    public void setStatus(EntityStatus status) {
        this.status = status;
    }
    
    public Long getGroup() {
        return group;
    }
    
    public void setGroup(Long group) {
        this.group = group;
    }
    
    public List<Long> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
    
}
