package com.wangdm.ui.service.impl;

import java.io.Serializable;
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
import com.wangdm.ui.constant.MenuType;
import com.wangdm.ui.dto.MenuDto;
import com.wangdm.ui.dto.MenuShowDto;
import com.wangdm.ui.entity.Menu;
import com.wangdm.ui.query.MenuQuery;
import com.wangdm.ui.service.MenuService;

@Service("menuService")
@Transactional
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private Dao<Menu> menuDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;

    @Override
    public Dto findById(Serializable id) {
    	Menu entity = menuDao.findById(Menu.class, id);
    	MenuDto dto = new MenuDto();
    	dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        MenuQuery query = (MenuQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(Menu.class);

        if(query.getParentId()!=null){
            constraint.addEqualCondition("parent.id", query.getParentId());
        }

        if(query.getType()!=null){
            constraint.addEqualCondition("type", query.getType());
        }
        
        if (query.getStatus() != null){
            constraint.addEqualCondition("status", query.getStatus());
        }

        if (query.getOrder() != null){
            constraint.setOrderProperty(query.getOrder());
        }

        constraint.setPageSize(query.getPageSize());
        constraint.setCurrentPage(query.getCurrentPage());
        
        List<Menu> entityList = menuDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        query.setTotalCount(constraint.getTotalCount());
        
        List<Dto> dtoList = new ArrayList<Dto>();
        for(Menu entity : entityList){
            MenuDto dto = new MenuDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public List<MenuShowDto> showChildrenMenu(Serializable id) {
        
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

    @Override
    public List<MenuShowDto> showAdminMainMenu() {
        
        Constraint constraint = constraintFactory.createConstraint(Menu.class);

        constraint.addEqualCondition("parent", null);
        
        constraint.addEqualCondition("display", true);
        
        constraint.addEqualCondition("type", MenuType.MENU);
        
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
            List<MenuShowDto> childrenDto = this.showChildrenMenu(menu.getId());
            dto.setChildren(childrenDto);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

    @Override
    public List<MenuShowDto> showNavigationMenu() {
        
        Constraint constraint = constraintFactory.createConstraint(Menu.class);

        constraint.addEqualCondition("parent", null);
        
        constraint.addEqualCondition("display", true);
        
        constraint.addEqualCondition("type", MenuType.NAVIGATION);
        
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
            List<MenuShowDto> childrenDto = this.showChildrenMenu(menu.getId());
            dto.setChildren(childrenDto);
            dtoList.add(dto);
        }
        
        return dtoList;
    }
    
}
