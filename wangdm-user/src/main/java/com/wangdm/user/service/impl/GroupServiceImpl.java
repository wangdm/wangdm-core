package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.user.dto.GroupDto;
import com.wangdm.user.entity.Group;
import com.wangdm.user.service.GroupService;

public class GroupServiceImpl implements GroupService {
    
    private static final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private BaseDao<Group> baseDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Serializable create(Dto dto) {
        Group gourp = (Group)dto.toEntity(Group.class);
        return baseDao.create(gourp);
    }

    @Override
    public void update(Dto dto) {
        Long id = dto.getEntityId();
        if(id==null || id.longValue()<=0){
           log.warn(dto.getClass().getName()+" must reimplement getEntityId()");
           return;
        }
        Group group = baseDao.findById(id);
        dto.toEntity(group);
        baseDao.update(group);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public Dto findById(Serializable id) {
        GroupDto dto = new GroupDto();
        Group group = baseDao.findById(id);
        dto.fromEntity(group);
        return dto;
    }

    @Override
    public List<Dto> query(BaseQuery query) {
        
        Constraint constraint = constraintFactory.createConstraint(Group.class);

        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        List<Group> groupList = baseDao.findByConstraint(constraint);
        
        if(groupList == null || groupList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(groupList.size());
        for(Group group : groupList){
            Dto dto = new GroupDto();
            dto.fromEntity(group);
            dtoList.add(null);
        }
        
        return dtoList;
    }

}
