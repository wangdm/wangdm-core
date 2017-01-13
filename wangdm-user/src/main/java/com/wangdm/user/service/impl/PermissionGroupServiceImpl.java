package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.PermissionGroupDto;
import com.wangdm.user.entity.PermissionGroup;
import com.wangdm.user.service.PermissionGroupService;

@Service("permissionGroupService")
@Transactional
public class PermissionGroupServiceImpl extends BaseService<PermissionGroup> implements PermissionGroupService {

    private static final Logger log = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);

    @Autowired
    private Dao<PermissionGroup> baseDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;

    @Override
    public void delete(Serializable id) {
        
            baseDao.deleteById(id, PermissionGroup.class);
    }
    
    @Override
    public Dto findById(Serializable id) {

        PermissionGroup entity = baseDao.findById(id, PermissionGroup.class);
        if(entity == null){
            log.info("No such PermissionGroup[id="+id+"] is found");
            return null;
        }
        
        PermissionGroupDto dto = new PermissionGroupDto();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public QueryResult query(Query q) {
        
        Constraint constraint = constraintFactory.createConstraint(PermissionGroup.class);
         
        constraint.addEqualCondition("status", EntityStatus.NORMAL);
        
        List<PermissionGroup> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(PermissionGroup entity : entityList){
            Dto dto = new PermissionGroupDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return new QueryResult(1,dtoList.size(),dtoList.size(),dtoList);
    }

}
