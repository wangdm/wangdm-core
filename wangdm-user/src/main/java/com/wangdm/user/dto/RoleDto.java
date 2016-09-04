package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.Role;

public class RoleDto extends BaseDto {

	@DtoMapper(entity=Role.class, field="id")
	private String id;

	@DtoMapper(entity=Role.class, field="name")
	private String name;

	@DtoMapper(entity=Role.class, field="title")
	private String title;

	@DtoMapper(entity=Role.class, field="desc")
	private String desc;

	@DtoMapper(entity=Role.class, field="type")
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
