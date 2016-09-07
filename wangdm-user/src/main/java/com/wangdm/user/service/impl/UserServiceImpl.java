package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.dto.UserAccountDto;
import com.wangdm.user.dto.UserLoginDto;
import com.wangdm.user.dto.UserProfileDto;
import com.wangdm.user.dto.UserRegisterDto;
import com.wangdm.user.entity.Certification;
import com.wangdm.user.entity.Role;
import com.wangdm.user.entity.RolePermission;
import com.wangdm.user.entity.User;
import com.wangdm.user.entity.UserProfile;
import com.wangdm.user.entity.UserRole;
import com.wangdm.user.query.UserQuery;
import com.wangdm.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService,InitializingBean{
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userDao")
    private BaseDao<User> userDao;

    @Autowired
    private BaseDao<UserProfile> profileDao;

    @Autowired
    private BaseDao<Certification> certificationDao;
    
    @Autowired
    private BaseDao<Role> roleDao;
    
    @Autowired
    private BaseDao<RolePermission> rolePermissionDao;

    @Autowired
    private BaseDao<UserRole> userRoleDao;
    
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
        
        if(dto instanceof UserAccountDto){
            User user = userDao.findById(User.class, id);
            dto.toEntity(user);
            userDao.update(user);
        }else if(dto instanceof UserProfileDto){
            UserProfile profile = profileDao.findById(User.class, id);
            if(profile==null){
                profile = (UserProfile) dto.toEntity(UserProfile.class);
                profileDao.create(profile);
            }else{
                dto.toEntity(profile);
                profileDao.update(profile);
            }
        }else if(dto instanceof Certification){
            Certification certification = certificationDao.findById(User.class, id);
            if(certification == null){
                certification = (Certification)dto.toEntity(Certification.class);
                certificationDao.create(certification);
            }else{
                dto.toEntity(certification);
                certificationDao.update(certification);
            }
        }
    }

    @Override
    public void delete(Serializable id) {

        User user = userDao.findById(User.class, id);
        if(user!=null){
            user.setStatus(EntityStatus.DELETE);
            userDao.update(user);
        }
    }

    @Override
    public void erase(Serializable id) {
        
        certificationDao.delete(User.class, id);
        profileDao.delete(User.class, id);
        userDao.delete(User.class, id);
        
    }

    @Override
    public void restore(Serializable id) {

        User user = userDao.findById(User.class, id);
        if(user!=null){
            user.setStatus(EntityStatus.NORMAL);
            userDao.update(user);
        }
        
    }

    @Override
    public Dto findById(Serializable id) {
        
        UserProfile profile = profileDao.findById(User.class, id);
        if(profile == null){
            log.info("No such user[id="+id+"] is found");
            return null;
        }
        
        UserProfileDto dto = new UserProfileDto();
        dto.fromEntity(profile);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        UserQuery query = (UserQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(User.class);

        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        List<User> userList = userDao.findByConstraint(constraint);
        if(userList == null || userList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(userList.size());
        for(User user : userList){
            Dto dto = new UserAccountDto();
            dto.fromEntity(user);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public void assignRole(Long userId, Long roleId) {
        
        do{
            Constraint constraint = constraintFactory.createConstraint(UserRole.class);
            constraint.addEqualCondition("user.id", userId);
            constraint.addEqualCondition("role.id", roleId);
            List<UserRole> roleList = userRoleDao.findByConstraint(constraint);
            if(roleList!=null && roleList.size()>=1){
                UserRole userRole = roleList.get(0);
                if(userRole.getStatus()!=EntityStatus.NORMAL){
                    userRole.setStatus(EntityStatus.NORMAL);
                    userRoleDao.update(userRole);
                }
                break;
            }
            
            User user = userDao.findById(User.class, userId);
            if(user == null){
                break;
            }
            
            Role role = roleDao.findById(Role.class, roleId);
            if(role == null){
                break;
            }
            
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setStatus(EntityStatus.NORMAL);
            userRoleDao.create(userRole);
            
            
        }while(false);
        
    }

    @Override
    public void removeRole(Long userId, Long roleId) {
        
        do{
            Constraint constraint = constraintFactory.createConstraint(UserRole.class);
            constraint.addEqualCondition("user.id", userId);
            constraint.addEqualCondition("role.id", roleId);
            
            List<UserRole> roleList = userRoleDao.findByConstraint(constraint);
            if(roleList==null || roleList.size()<=0){
                break;
            }

            for(UserRole role : roleList){
                userRoleDao.delete(role);
            }
            
            
        }while(false);
        
    }

    @Override
    public List<RoleDto> listRole(Long userId) {
        
        Constraint constraint = constraintFactory.createConstraint(UserRole.class);
        constraint.addEqualCondition("user.id", userId);
        constraint.addNonCondition("status", EntityStatus.DELETE);
        
        List<UserRole> roleList = userRoleDao.findByConstraint(constraint);
        if(roleList==null || roleList.size()<=0){
            return null;
        }

        List<RoleDto> dtoList = new ArrayList<RoleDto>(roleList.size());
        for(UserRole role : roleList){
            RoleDto dto = new RoleDto();
            dto.fromEntity(role.getRole());
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public List<PermissionDto> listPermission(Long userId) {
        
        Constraint constraint = constraintFactory.createConstraint(UserRole.class);
        constraint.addEqualCondition("user.id", userId);
        constraint.addEqualCondition("status", EntityStatus.NORMAL);
        
        List<UserRole> roleList = userRoleDao.findByConstraint(constraint);
        if(roleList==null || roleList.size()<=0){
            return null;
        }
        
        List<RolePermission> rolePermissionList = new ArrayList<RolePermission>(20);
        for(UserRole userRole : roleList){
            Role role = userRole.getRole();
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
    public void login(UserLoginDto loginDto) {
        // TODO Auto-generated method stub
        log.warn("Unimplement Service Method!");
        
    }

    @Override
    public void register(UserRegisterDto registerDto) {
        // TODO Auto-generated method stub
        log.warn("Unimplement Service Method!");
        
    }

    @Override
    public boolean isNameUsable(String name) {
        
        List<User> userList = userDao.findByColumn("username", name);
        if(userList!=null && userList.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isPhoneUsable(String phone) {
        
        List<User> userList = userDao.findByColumn("phone", phone);
        if(userList!=null && userList.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailUsable(String email) {
        
        List<User> userList = userDao.findByColumn("email", email);
        if(userList!=null && userList.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCertificationUsable(String code) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userDao.setClazz(User.class);
    }
}
