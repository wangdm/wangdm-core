package com.wangdm.user.dto;

import java.util.List;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.Group;

public class GroupTreeDto extends BaseDto {

	@DtoMapper(entity=Group.class, field="id")
	private String id;

	@DtoMapper(entity=Group.class, field="name")
	private String name;
	
	private List<GroupTreeDto> children;

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

	public List<GroupTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<GroupTreeDto> children) {
		this.children = children;
	}
	
}
