package com.wangdm.user.dto;

import java.util.List;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.PermissionGroup;

public class PermissionGroupDto extends BaseDto {

	@DtoMapper(entity=PermissionGroup.class, field="id")
	private String id;

	@DtoMapper(entity=PermissionGroup.class, field="name")
	private String name;

	@DtoMapper(entity=PermissionGroup.class, field="desc")
	private String desc;
	
	List<Dto> permList;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Dto> getPermList() {
		return permList;
	}

	public void setPermList(List<Dto> permList) {
		this.permList = permList;
	}
}
