package com.wangdm.user.service;

import java.util.List;

import com.wangdm.core.dto.Dto;
import com.wangdm.core.service.Service;

public interface PermissionService extends Service {
    
    public List<Dto> getPermission(Integer groupId);

}
