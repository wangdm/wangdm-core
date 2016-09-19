package com.wangdm.ui.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.constraint.Order.OrderType;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.service.BaseService;
import com.wangdm.ui.dto.MenuDto;
import com.wangdm.ui.dto.MenuShowDto;
import com.wangdm.ui.entity.Menu;
import com.wangdm.ui.service.MenuService;

@Service("menuService")
@Transactional
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private Dao<Menu> menuDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;

    @Override
    public Serializable create(Dto dto) {
        
        MenuDto menuDto = (MenuDto)dto;
        
        Menu menu = (Menu)menuDto.toEntity(Menu.class);
        if(menu.getParent() != null){
            if((menu.getParent().getId()==null) || (menu.getParent().getId().longValue()<=0)){
                menu.setParent(null);
            }
        }
        
        menu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        
        return menuDao.create(menu);
    }

    @Override
    public void update(Dto dto) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Serializable id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Dto findById(Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Dto> query(Query q) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MenuShowDto> showAdminMainMenu() {
        
        Constraint constraint = constraintFactory.createConstraint(Menu.class);

        constraint.addEqualCondition("parent", null);
        
        constraint.addEqualCondition("display", true);
        
        List<EntityStatus> entityTypeList = new ArrayList<EntityStatus>();
        entityTypeList.add(EntityStatus.NORMAL);
        constraint.addEqualCondition("status", entityTypeList);
        
        constraint.addOrder("idx", OrderType.ASC);
        
        List<Menu> menuList = menuDao.findByConstraint(constraint);
        
        if(menuList == null || menuList.size()<=0){
            return null;
        }
        
        List<MenuShowDto> dtoList = new ArrayList<MenuShowDto>(menuList.size());
        for(Menu menu : menuList){
            MenuShowDto dto = new MenuShowDto();
            dto.fromEntity(menu);
            List<MenuShowDto> childrenDto = this.showAdminChildrenMenu(menu.getId());
            dto.setChildren(childrenDto);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public List<MenuShowDto> showAdminChildrenMenu(Serializable id) {
        
        Constraint constraint = constraintFactory.createConstraint(Menu.class);

        constraint.addEqualCondition("parent.id", id);
        
        constraint.addEqualCondition("display", true);
        
        List<EntityStatus> entityTypeList = new ArrayList<EntityStatus>();
        entityTypeList.add(EntityStatus.NORMAL);
        constraint.addEqualCondition("status", entityTypeList);
        
        constraint.addOrder("idx", OrderType.ASC);
        
        List<Menu> menuList = menuDao.findByConstraint(constraint);
        
        if(menuList == null || menuList.size()<=0){
            return null;
        }
        
        List<MenuShowDto> dtoList = new ArrayList<MenuShowDto>(menuList.size());
        for(Menu menu : menuList){
            MenuShowDto dto = new MenuShowDto();
            dto.fromEntity(menu);
            dtoList.add(dto);
        }
        
        return dtoList;
    }
    
}
