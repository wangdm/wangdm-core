package com.wangdm.ui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.ui.dto.MenuDto;
import com.wangdm.ui.dto.MenuShowDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class MenuServiceTest {
    
    @Autowired
    MenuService menuService;

    @Test
    public void testCreate() {
        MenuDto dto = new MenuDto();
        dto.setName("课程分类");
        dto.setUrl("listcategory");
        dto.setIcon("fsdfd");
        dto.setParentId("8");
        dto.setDisplay(true);
        menuService.create(dto);
        
        dto = new MenuDto();
        dto.setName("课程管理");
        dto.setUrl("listcourse");
        dto.setIcon("fsdfd");
        dto.setParentId("8");
        dto.setDisplay(true);
        menuService.create(dto);
        
        dto = new MenuDto();
        dto.setName("视频管理");
        dto.setUrl("listvideo");
        dto.setIcon("fsdfd");
        dto.setParentId("8");
        dto.setDisplay(true);
        menuService.create(dto);
        
        dto = new MenuDto();
        dto.setName("直播管理");
        dto.setUrl("listlive");
        dto.setIcon("fsdfd");
        dto.setParentId("8");
        dto.setDisplay(true);
        menuService.create(dto);
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindById() {
        fail("Not yet implemented");
    }

    @Test
    public void testQuery() {
        fail("Not yet implemented");
    }

    @Test
    public void testShowAdminMainMenu() {
        List<MenuShowDto> dtoList = menuService.showAdminMainMenu();
        if(dtoList!=null && dtoList.size()>0){
            for(MenuShowDto dto : dtoList){
                System.out.println(dto.toString());
            }
        }
    }

    @Test
    public void testShowAdminChildrenMenu() {
        List<MenuShowDto> dtoList = menuService.showAdminChildrenMenu(Long.valueOf(3));
        if(dtoList!=null && dtoList.size()>0){
            for(MenuShowDto dto : dtoList){
                System.out.println(dto.toString());
            }
        }
    }

}
