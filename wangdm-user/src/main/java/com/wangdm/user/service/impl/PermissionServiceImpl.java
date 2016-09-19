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
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.entity.Permission;
import com.wangdm.user.query.PermissionQuery;
import com.wangdm.user.service.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private Dao<Permission> baseDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {

        Permission entity = baseDao.findById(Permission.class, id);
        if(entity == null){
            log.info("No such permission[id="+id+"] is found");
            return null;
        }
        
        PermissionDto dto = new PermissionDto();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        PermissionQuery query = (PermissionQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Permission.class);
        
        if(query.getGroupId()!=null)
            constraint.addEqualCondition("group.id", query.getGroupId());
            
        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        constraint.setPageSize(query.getPageSize());
        
        constraint.setCurrentPage(query.getCurrentPage());
        
        List<Permission> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(Permission entity : entityList){
            Dto dto = new PermissionDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

}
