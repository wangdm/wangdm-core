package com.wangdm.user.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;

public interface GroupService extends Service {
    
    public void assignRole(Long groupId, Long roleId);
    
    public void removeRole(Long groupId, Long roleId);
    
    public List<RoleDto> listRole(Long groupId);
    
    public List<PermissionDto> listPermission(Long groupId);
    
    public void groupUser(Long groupId, Long userId);

}
