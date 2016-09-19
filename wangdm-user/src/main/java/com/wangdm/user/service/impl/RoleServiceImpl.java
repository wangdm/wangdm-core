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
import com.wangdm.core.query.BaseQuery;
import com.wangdm.core.query.Query;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.dto.RolePermissionDto;
import com.wangdm.user.entity.Permission;
import com.wangdm.user.entity.Role;
import com.wangdm.user.entity.RolePermission;
import com.wangdm.user.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private Dao<Role> baseDao;
    
    @Autowired
    private Dao<Permission> permissionDao;
    
    @Autowired
    private Dao<RolePermission> rolePermissionDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {

        Role entiry = baseDao.findById(Role.class, id);
        if(entiry == null){
            log.info("No such role[id="+id+"] is found");
            return null;
        }
        
        RoleDto dto = new RoleDto();
        dto.fromEntity(entiry);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        BaseQuery query = (BaseQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Role.class);
            
        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        constraint.setPageSize(query.getPageSize());
        
        constraint.setCurrentPage(query.getCurrentPage());
        
        List<Role> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(Role entity : entityList){
            Dto dto = new RoleDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public List<PermissionDto> listPermission(Long roleId) {
        
        Constraint constraint = constraintFactory.createConstraint(RolePermission.class);
        constraint.addEqualCondition("status", EntityStatus.NORMAL);
        constraint.addEqualCondition("role.id", roleId);
        
        List<RolePermission> rolePermissionList = rolePermissionDao.findByConstraint(constraint);
        if(rolePermissionList == null || rolePermissionList.size()<=0){
            return null;
        }
        
        List<PermissionDto> dtoList = new ArrayList<PermissionDto>(rolePermissionList.size());
        for(RolePermission rolePermission : rolePermissionList){
            PermissionDto dto = new PermissionDto();
            dto.fromEntity(rolePermission.getPermission());
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public void assignPermission(RolePermissionDto perm) {
        
        Long roleId = Long.valueOf(perm.getRoleId());
        
        Long permId = Long.valueOf(perm.getPermId());
        
        Constraint constraint = constraintFactory.createConstraint(RolePermission.class);
        constraint.addEqualCondition("role.id", roleId);
        constraint.addEqualCondition("permission.id", permId);
        
        RolePermission rolePermission = null;
        
        List<RolePermission> rolePermissionList = rolePermissionDao.findByConstraint(constraint);
        if(rolePermissionList==null || rolePermissionList.size()<=0){
            rolePermission = new RolePermission();
            rolePermission.setRole(baseDao.findById(Role.class, roleId));
            Permission p = permissionDao.findById(Permission.class, permId);
            perm.toEntity(p);
            rolePermission.setPermission(p);
            rolePermissionDao.create(rolePermission);
        }else{
            rolePermission = rolePermissionList.get(0);
            if(rolePermission.getStatus()!=EntityStatus.NORMAL){
                rolePermission.setStatus(EntityStatus.NORMAL);
                rolePermissionDao.update(rolePermission);
            }
        }
        
    }

    @Override
    public void assignPermission(List<RolePermissionDto> perms) {
        
        for(RolePermissionDto perm : perms){
            assignPermission(perm);
        }
        
    }

    @Override
    public void removePermission(RolePermissionDto perm) {
        
        Long roleId = Long.valueOf(perm.getRoleId());
        
        Long permId = Long.valueOf(perm.getPermId());
        
        Constraint constraint = constraintFactory.createConstraint(RolePermission.class);
        constraint.addEqualCondition("role.id", roleId);
        constraint.addEqualCondition("permission.id", permId);
        
        List<RolePermission> rolePermissionList = rolePermissionDao.findByConstraint(constraint);
        if(rolePermissionList!=null && rolePermissionList.size()<=0){
            RolePermission rolePermission = rolePermissionList.get(0);
            rolePermissionDao.delete(rolePermission);
        }
    }

    @Override
    public void removePermission(List<RolePermissionDto> perms) {
        
        for(RolePermissionDto perm : perms){
            removePermission(perm);
        }
        
    }

    @Override
    public void resetPermission(Long roleId) {
        // TODO Auto-generated method stub
        log.warn("Unimplement Service Method!");
        
    }

}
