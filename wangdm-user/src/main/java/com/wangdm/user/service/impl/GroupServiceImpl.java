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
import com.wangdm.user.dto.GroupDto;
import com.wangdm.user.dto.GroupTreeDto;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.entity.Group;
import com.wangdm.user.entity.GroupRole;
import com.wangdm.user.entity.Role;
import com.wangdm.user.entity.RolePermission;
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
    private Dao<GroupRole> groupRoleDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {
        
        Group entity = baseDao.findById(id, Group.class);
        if(entity == null){
            log.info("No such group[id="+id+"] is found");
            return null;
        }
        
        GroupDto dto = new GroupDto();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public QueryResult query(Query q) {

        log.warn("Unimplement mathod!!!");
        return null;
    }

    @Override
    public void assignRole(Long groupId, Long roleId) {
        
        do{
            Constraint constraint = constraintFactory.createConstraint(GroupRole.class);
            constraint.addEqualCondition("group.id", groupId);
            constraint.addEqualCondition("role.id", roleId);
            List<GroupRole> roleList = groupRoleDao.findByConstraint(constraint);
            if(roleList!=null && roleList.size()>=1){
                break;
            }
            
            Group group = baseDao.findById(groupId, Group.class);
            if(group == null){
                break;
            }
            
            Role role = roleDao.findById(roleId, Role.class);
            if(role == null){
                break;
            }
            
            GroupRole groupRole = new GroupRole();
            groupRole.setGroup(group);
            groupRole.setRole(role);
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

	private List<Group> recursionChildrenGroup(Long groupId) {
		
		if(groupId==null || groupId.longValue()<=0){
			return null;
		}

        Constraint constraint = constraintFactory.createConstraint(Group.class);
        constraint.addEqualCondition("parent.id", groupId);
		List<Group> groupList = baseDao.findByConstraint(constraint);
		
		if(groupList!=null && groupList.size()>0){
			for(int i=0; i<groupList.size(); i++){
				Group group = groupList.get(i);
				Long id = group.getId();
				group.setChildren(recursionChildrenGroup(id));
			}
		}
		
		return groupList;
	}
	
	private GroupTreeDto recursionGroupToDto(Group group){
		
		if(group==null){
			return null;
		}
		
		GroupTreeDto dto = new GroupTreeDto();
		dto.fromEntity(group);
		List<Group> children = group.getChildren();
		if(children!=null && children.size()>0){
			for(int i=0; i<children.size(); i++){
				Group child = children.get(i);
				GroupTreeDto tmp = recursionGroupToDto(child);
				if(dto!=null){
					if(dto.getChildren()==null){
						dto.setChildren(new ArrayList<GroupTreeDto>());
					}
					dto.getChildren().add(tmp);
				}
			}
		}
		
		return dto;
	}

	@Override
	public GroupTreeDto getGroupTree(Long groupId) {

		Group group = null;
		
        Constraint constraint = constraintFactory.createConstraint(Group.class);
        
		if(groupId==null || groupId.longValue()<=0){
	        constraint.addEqualCondition("parent.id", null);
	        List<Group> groupList = baseDao.findByConstraint(constraint);

			if(groupList!=null && groupList.size()>0){
				group = groupList.get(0);
			}
		}else{
			group = baseDao.findById(groupId, Group.class);
		}
		
		if(group!=null){
			group.setChildren(recursionChildrenGroup(group.getId()));
			return recursionGroupToDto(group);
		}
		
		return null;
	}

	@Override
	public List<GroupDto> getChildrenGroup(Long groupId) {
		
		if(groupId==null || groupId.longValue()<=0){
			return null;
		}
        
        Constraint constraint = constraintFactory.createConstraint(Group.class);
		constraint.addEqualCondition("parent.id", groupId);
        
        List<Group> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<GroupDto> dtoList = new ArrayList<GroupDto>(entityList.size());
        for(Group entity : entityList){
        	GroupDto dto = new GroupDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
	}
}
