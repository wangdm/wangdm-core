package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.Certification;

public class CertificationDto extends BaseDto {

	@DtoMapper(entity=Certification.class, field="user.id")
	private String id;

	@DtoMapper(entity=Certification.class, field="realname")
	private String realname;

	@DtoMapper(entity=Certification.class, field="number")
	private String number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
