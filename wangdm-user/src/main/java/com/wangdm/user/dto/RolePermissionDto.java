package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.RolePermission;

public class RolePermissionDto extends BaseDto {

	@DtoMapper(entity=RolePermission.class, field="id")
	private String id;

	@DtoMapper(entity=RolePermission.class, field="role.id")
    private String roleId;

	@DtoMapper(entity=RolePermission.class, field="permission.id")
    private String permId;

	@DtoMapper(entity=RolePermission.class, field="permission.name")
    private String permName;

    @Override
    public Long getEntityId() {
        return Long.valueOf(id);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
