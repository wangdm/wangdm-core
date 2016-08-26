package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.user.entity.User;
import com.wangdm.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    
    @Autowired
    private BaseDao<User> baseDao;

    @Override
    public Serializable create(Dto dto) {
        User user = (User)dto.toEntity(User.class);
        return baseDao.create(user);
    }

    @Override
    public void update(Dto dto) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Serializable id) {
        
        baseDao.delete(id);
    }

    @Override
    public Dto findById(Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Dto> query(BaseQuery query) {
        // TODO Auto-generated method stub
        return null;
    }

}
