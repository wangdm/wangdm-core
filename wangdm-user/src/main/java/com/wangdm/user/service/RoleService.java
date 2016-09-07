package com.wangdm.user.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.user.dto.PermissionDto;

public interface RoleService extends Service {
    
    public List<PermissionDto> listPermission(Long roleId);

    public void assignPermission(Long roleId, PermissionDto perm);

    public void assignPermission(Long roleId, List<PermissionDto> perms);
    
    public void removePermission(Long roleId, PermissionDto perm);
    
    public void removePermission(Long roleId, List<PermissionDto> perms);
    
    public void resetPermission(Long roleId);
}
