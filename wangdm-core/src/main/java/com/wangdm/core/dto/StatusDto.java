package com.wangdm.core.dto;

import com.wangdm.core.dto.BaseDto;

public class StatusDto extends BaseDto {
	
	private int code = 0;
	
	private String result = "success";
	
	private String description = "";
	
	public StatusDto(){}

	public StatusDto(int code, String result, String description) {
		super();
		this.code = code;
		this.result = result;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

    public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public Long getEntityId() {
        return null;
    }

}
