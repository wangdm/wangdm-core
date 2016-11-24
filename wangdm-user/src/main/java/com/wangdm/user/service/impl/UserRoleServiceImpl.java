package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.UserRoleDto;
import com.wangdm.user.entity.UserRole;
import com.wangdm.user.service.UserRoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService{
    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    
    @Autowired
    private Dao<UserRole>  userRoleDao;
    
    @Override
    public Dto findById(Serializable id) {
        UserRole userRole = userRoleDao.findById(UserRole.class, id);
        UserRoleDto dto = new UserRoleDto();
        dto.fromEntity(userRole);
        return dto;
    }

    @Override
    public List<Dto> query(Query query) {
        return null;
    }


    

}
