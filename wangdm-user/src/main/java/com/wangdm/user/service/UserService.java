package com.wangdm.user.service;

import com.wangdm.core.query.BaseQuery;
import com.wangdm.core.service.Service;
import com.wangdm.user.dto.UserLoginDto;
import com.wangdm.user.dto.UserRegisterDto;

public interface UserService extends Service<BaseQuery> {

	public void login(UserLoginDto loginDto);
	
	public void register(UserRegisterDto registerDto);
}
