package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.UserRole;

public class UserRoleDto extends BaseDto {
	
	@DtoMapper(entity=UserRole.class, field="id")
	private String id;
	
	@DtoMapper(entity=UserRole.class, field="user.id")
	private String userId;
	
	@DtoMapper(entity=UserRole.class, field="role.id")
	private String roleId;
	

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


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRoleId() {
        return roleId;
    }


    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

	
}
