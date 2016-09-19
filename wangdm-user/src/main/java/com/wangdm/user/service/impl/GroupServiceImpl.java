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
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.GroupDto;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.entity.Group;
import com.wangdm.user.entity.GroupRole;
import com.wangdm.user.entity.Role;
import com.wangdm.user.entity.RolePermission;
import com.wangdm.user.entity.User;
import com.wangdm.user.entity.UserGroup;
import com.wangdm.user.query.GroupQuery;
import com.wangdm.user.service.GroupService;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends BaseService<Group> implements GroupService {
    
    private static final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    private Dao<Group> baseDao;
    
    @Autowired
    private Dao<Role> roleDao;
    
    @Autowired
    private Dao<RolePermission> rolePermissionDao;
    
    @Autowired
    private Dao<User> userDao;

    @Autowired
    private Dao<UserGroup> userGroupDao;

    @Autowired
    private Dao<GroupRole> groupRoleDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {
        
        Group entity = baseDao.findById(Group.class, id);
        if(entity == null){
            log.info("No such group[id="+id+"] is found");
            return null;
        }
        
        GroupDto dto = new GroupDto();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        GroupQuery query = (GroupQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Group.class);
        
        if(query.getParentId()!=null)
            constraint.addEqualCondition("parent.id", query.getParentId());
            
        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        constraint.setPageSize(query.getPageSize());
        
        constraint.setCurrentPage(query.getCurrentPage());
        
        List<Group> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(Group entity : entityList){
            Dto dto = new GroupDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public void assignRole(Long groupId, Long roleId) {
        
        do{
            Constraint constraint = constraintFactory.createConstraint(GroupRole.class);
            constraint.addEqualCondition("group.id", groupId);
            constraint.addEqualCondition("role.id", roleId);
            List<GroupRole> roleList = groupRoleDao.findByConstraint(constraint);
            if(roleList!=null && roleList.size()>=1){
                GroupRole groupRole = roleList.get(0);
                if(groupRole.getStatus()!=EntityStatus.NORMAL){
                    groupRole.setStatus(EntityStatus.NORMAL);
                    groupRoleDao.update(groupRole);
                }
                break;
            }
            
            Group group = baseDao.findById(Group.class, groupId);
            if(group == null){
                break;
            }
            
            Role role = roleDao.findById(Role.class, roleId);
            if(role == null){
                break;
            }
            
            GroupRole groupRole = new GroupRole();
            groupRole.setGroup(group);
            groupRole.setRole(role);
            groupRole.setStatus(EntityStatus.NORMAL);
            groupRoleDao.create(groupRole);
            
            
        }while(false);
    }

    @Override
    public void removeRole(Long groupId, Long roleId) {
        
        do{
            Constraint constraint = constraintFactory.createConstraint(GroupRole.class);
            constraint.addEqualCondition("group.id", groupId);
            constraint.addEqualCondition("role.id", roleId);
            
            List<GroupRole> roleList = groupRoleDao.findByConstraint(constraint);
            if(roleList==null || roleList.size()<=0){
                break;
            }

            for(GroupRole role : roleList){
                groupRoleDao.delete(role);
            }
            
            
        }while(false);
        
    }

    @Override
    public List<RoleDto> listRole(Long groupId){
        
        Constraint constraint = constraintFactory.createConstraint(GroupRole.class);
        constraint.addEqualCondition("group.id", groupId);
        constraint.addNonCondition("status", EntityStatus.DELETE);
        
        List<GroupRole> roleList = groupRoleDao.findByConstraint(constraint);
        if(roleList==null || roleList.size()<=0){
            return null;
        }

        List<RoleDto> dtoList = new ArrayList<RoleDto>(roleList.size());
        for(GroupRole role : roleList){
            RoleDto dto = new RoleDto();
            dto.fromEntity(role.getRole());
            dtoList.add(dto);
        }
        
        return dtoList;
        
    }

    @Override
    public List<PermissionDto> listPermission(Long groupId) {
        
        Constraint constraint = constraintFactory.createConstraint(GroupRole.class);
        constraint.addEqualCondition("group.id", groupId);
        constraint.addEqualCondition("status", EntityStatus.NORMAL);
        
        List<GroupRole> roleList = groupRoleDao.findByConstraint(constraint);
        if(roleList==null || roleList.size()<=0){
            return null;
        }
        
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>(20);
        for(GroupRole groupRole : roleList){
            Role role = groupRole.getRole();
            constraint.clear();
            constraint.setEntityClass(RolePermission.class);
            constraint.addEqualCondition("role", role);
            constraint.addEqualCondition("status", EntityStatus.NORMAL);
            List<RolePermission> list= rolePermissionDao.findByConstraint(constraint);
            if(list!=null && list.size()>0){
                rolePermissionList.addAll(list);
            }
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
    public void groupUser(Long groupId, Long userId) {
        
        if(groupId==null || groupId.longValue()<=0){
            userGroupDao.delete(UserGroup.class, userId);
        }
        
        UserGroup userGroup = userGroupDao.findById(UserGroup.class, userId);
        if(userGroup == null){
            userGroup = new UserGroup();
            User user = userDao.findById(User.class, userId);
            userGroup.setUser(user);
            userGroup.setGroup(baseDao.findById(Group.class, groupId));
            userGroupDao.create(userGroup);
        }else{
            if(userGroup.getGroup().getId()!=groupId){
                userGroup.setGroup(baseDao.findById(Group.class, groupId));
                userGroupDao.update(userGroup);
            }
        }
        
    }
}
