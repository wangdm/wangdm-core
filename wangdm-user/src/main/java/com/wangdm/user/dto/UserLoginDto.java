package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;

public class UserLoginDto extends BaseDto {
	
	private String loginname;
	
	private String loginpwd;
	
	private String captcha;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
