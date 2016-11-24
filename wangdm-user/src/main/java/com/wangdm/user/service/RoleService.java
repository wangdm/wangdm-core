package com.wangdm.user.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.user.dto.PermissionDto;

public interface RoleService extends Service {
    
    public List<PermissionDto> getPermission(Long roleId);
    
    public void setPermission(Long roleId, List<PermissionDto> permList);
    
    public void resetPermission(Long roleId);
}
