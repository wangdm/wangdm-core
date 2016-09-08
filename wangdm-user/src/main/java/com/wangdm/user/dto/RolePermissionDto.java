package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;

public class RolePermissionDto extends BaseDto {
    
    private String roleId;
    
    private String permId;
    
    private String permName;

    @Override
    public Long getEntityId() {
        return null;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

}
