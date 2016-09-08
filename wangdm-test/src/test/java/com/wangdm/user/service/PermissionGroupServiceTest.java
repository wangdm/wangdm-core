package com.wangdm.user.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.user.dto.PermissionGroupDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class PermissionGroupServiceTest {

    @Autowired
    PermissionGroupService permGroupService;
    
    @Test
    public void testFindById() {
        Dto dto = permGroupService.findById(Long.valueOf(2));
        System.out.println(dto.toString());
    }

    @Test
    public void testQuery() {
        BaseQuery query = new BaseQuery();
        List<Dto> dtoList = permGroupService.query(query);
        for(Dto dto : dtoList){
            System.out.println(dto.toString());
        }
    }

    @Test
    public void testCreate() {
        PermissionGroupDto dto = new PermissionGroupDto();
        
        dto.setName("用户管理");
        permGroupService.create(dto);
        
        dto.setName("角色管理");
        permGroupService.create(dto);
        
        dto.setName("权限管理");
        permGroupService.create(dto);
    }

    @Test
    public void testUpdate() {
        PermissionGroupDto dto = new PermissionGroupDto();
        
        //dto.setName("用户管理");
        //Long id = (Long) permGroupService.create(dto);
        
        dto.setId("5");
        dto.setName("系统设置");
        permGroupService.update(dto);
    }

    @Test
    public void testDelete() {
        permGroupService.delete(Long.valueOf(5));
    }

    @Test
    public void testErase() {
        permGroupService.erase(Long.valueOf(5));
    }

    @Test
    public void testRestore() {
        permGroupService.restore(Long.valueOf(5));
    }

}
