package com.wangdm.user.service;

import java.util.List;

import com.wangdm.core.service.Service;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RolePermissionDto;

public interface RoleService extends Service {
    
    public List<PermissionDto> listPermission(Long roleId);

    public void assignPermission(RolePermissionDto perm);

    public void assignPermission(List<RolePermissionDto> perms);
    
    public void removePermission(RolePermissionDto perm);
    
    public void removePermission(List<RolePermissionDto> perms);
    
    public void resetPermission(Long roleId);
}
