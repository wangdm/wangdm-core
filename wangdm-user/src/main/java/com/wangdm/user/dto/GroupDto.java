package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.Group;

public class GroupDto extends BaseDto {

	@DtoMapper(entity=Group.class, field="id")
	private String id;

	@DtoMapper(entity=Group.class, field="parent.id")
	private String parentId;

	@DtoMapper(entity=Group.class, field="name")
	private String name;

	@DtoMapper(entity=Group.class, field="desc")
	private String desc;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
}
