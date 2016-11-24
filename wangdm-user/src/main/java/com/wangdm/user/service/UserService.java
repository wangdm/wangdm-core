package com.wangdm.user.service;

import java.io.Serializable;
import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.dto.UserLoginDto;
import com.wangdm.user.dto.UserRegisterDto;
import com.wangdm.user.dto.UserSessionDto;

public interface UserService extends Service {
    
    public void verify(Serializable id);
    
    public void forbidden(Serializable id);
    
    public void assignRole(Long userId, Long roleId);
    
    public void removeRole(Long userId, Long roleId);
    
    public void moveGroup(Long userId, Long groupId);
    
    public List<RoleDto> listRole(Long userId);
    
    public List<PermissionDto> listPermission(Long userId);

    public UserSessionDto login(UserLoginDto loginDto);
    
    public void register(UserRegisterDto registerDto);
    
    public boolean isNameUsable(String name);
    
    public boolean isPhoneUsable(String phone);
    
    public boolean isEmailUsable(String email);
    
    public boolean isCertificationUsable(String code);
}
