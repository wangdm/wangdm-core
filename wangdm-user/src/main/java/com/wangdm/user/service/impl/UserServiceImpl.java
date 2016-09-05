package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.user.dto.UserLoginDto;
import com.wangdm.user.dto.UserRegisterDto;
import com.wangdm.user.entity.Certification;
import com.wangdm.user.entity.User;
import com.wangdm.user.entity.UserProfile;
import com.wangdm.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<UserProfile> profileDao;

    @Autowired
    private BaseDao<Certification> certificationDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Serializable create(Dto dto) {
        User user = (User)dto.toEntity(User.class);
        if(user == null){
            return null;
        }
        return userDao.create(user);
    }

    @Override
    public void update(Dto dto) {
        Long id = dto.getEntityId();
        if(id==null || id.longValue()<=0){
           log.warn(dto.getClass().getName()+" must reimplement getEntityId()");
           return;
        }
        
        User user = userDao.findById(id);
        dto.toEntity(user);
        userDao.update(user);
    }

    @Override
    public void delete(Serializable id) {
        
        userDao.delete(id);
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

	@Override
	public void login(UserLoginDto loginDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void register(UserRegisterDto registerDto) {
		// TODO Auto-generated method stub
		
	}

}
