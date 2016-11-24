package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.Permission;

public class PermissionDto extends BaseDto {
	
	@DtoMapper(entity=Permission.class, field="id")
	private String id;

	@DtoMapper(entity=Permission.class, field="name")
	private String name;

	@DtoMapper(entity=Permission.class, field="title")
	private String title;

	@DtoMapper(entity=Permission.class, field="desc")
	private String desc;

    @DtoMapper(entity=Permission.class, field="group.id")
    private String groupId;

    @DtoMapper(entity=Permission.class, field="group.name")
    private String groupName;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
