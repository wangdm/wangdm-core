package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.dao.BaseDao;
import com.wangdm.user.entity.User;
import com.wangdm.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    
    @Autowired
    private BaseDao<User> baseDao;

    @Override
    public Serializable create(User entity) {
        
        return baseDao.create(entity);
    }

    @Override
    public void update(User entity) {
        
        baseDao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        
        baseDao.delete(id);
    }

    @Override
    public User findById(Serializable id) {
        
        return baseDao.findById(id);
    }

    @Override
    public List<User> listAll() {

        return baseDao.listAll();
    }

}
