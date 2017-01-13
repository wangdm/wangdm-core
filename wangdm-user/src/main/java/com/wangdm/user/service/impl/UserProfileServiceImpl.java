package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.constant.Gender;
import com.wangdm.user.dto.UserAccountDto;
import com.wangdm.user.dto.UserProfileDto;
import com.wangdm.user.entity.UserProfile;
import com.wangdm.user.query.UserQuery;
import com.wangdm.user.service.UserProfileService;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl extends BaseService<UserProfile> implements UserProfileService{
    
    @SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    private Dao<UserProfile>  userProfileDao;


    @Autowired
    private ConstraintFactory constraintFactory;
 
  
    @Override
    public void delete(Serializable id) {
        UserProfile userProfile = userProfileDao.findById(id, UserProfile.class);
        if(userProfile!=null){
            userProfileDao.update(userProfile);
        }
    }

    @Override
    public void erase(Serializable id) {
        userProfileDao.deleteById(id, UserProfile.class);
    }

    @Override
    public void restore(Serializable id) {
        UserProfile userProfile = userProfileDao.findById(id, UserProfile.class);
        if(userProfile!=null){
            userProfileDao.update(userProfile);
        }
    }

    @Override
    public Dto findById(Serializable id) {
        UserProfile profile = userProfileDao.findById(id, UserProfile.class);
        UserProfileDto dto = new UserProfileDto();
        dto.fromEntity(profile);
        return dto;
    }
    

    @Override
    public QueryResult query(Query q) {
        UserQuery query = (UserQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(UserProfile.class);

        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());
        
        List<UserProfile> userList = userProfileDao.findByConstraint(constraint);
        if(userList == null || userList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(userList.size());
        for(UserProfile user : userList){
            Dto dto = new UserAccountDto();
            dto.fromEntity(user);
            dtoList.add(dto);
        }
        
        return new QueryResult(1,dtoList.size(),dtoList.size(),dtoList);
    }
    
    
    @Override
    public void update(Dto dto) {
        UserProfile userProfile = (UserProfile) dto.toEntity(UserProfile.class);
        UserProfileDto userProfileDto = (UserProfileDto) dto;
        if("SECRET".equals(userProfileDto.getGender())){
            userProfile.setGender(Gender.SECRET); 
        }
        
        if("MALE".equals(userProfileDto.getGender())){
            userProfile.setGender(Gender.MALE); 
        }
        
        if("FEMALE".equals(userProfileDto.getGender())){
            userProfile.setGender(Gender.FEMALE); 
        }
        userProfileDao.update(userProfile);
     }

}
