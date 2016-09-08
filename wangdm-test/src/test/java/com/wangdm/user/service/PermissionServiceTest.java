package com.wangdm.user.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.core.dto.Dto;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.query.PermissionQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class PermissionServiceTest {
    
    @Autowired
    PermissionService permService;

    @Test
    public void testFindById() {
        fail("Not yet implemented");
    }

    @Test
    public void testQuery() {
        PermissionQuery query = new PermissionQuery();
        query.setGroupId(1L);
        List<Dto> dtoList = permService.query(query);
        for(Dto dto : dtoList){
            System.out.println(dto.toString());
        }
    }

    @Test
    public void testCreate() {
        PermissionDto dto = new PermissionDto();
        
        dto.setGroupId("1");
        dto.setName("adduser");
        dto.setTitle("添加用户");
        permService.create(dto);
        
        dto.setGroupId("1");
        dto.setName("deluser");
        dto.setTitle("删除用户");
        permService.create(dto);
        
        dto.setGroupId("2");
        dto.setName("addrole");
        dto.setTitle("添加角色");
        permService.create(dto);
        
        dto.setGroupId("2");
        dto.setName("delrole");
        dto.setTitle("删除用户");
        permService.create(dto);
        
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
    public void testErase() {
        fail("Not yet implemented");
    }

    @Test
    public void testRestore() {
        fail("Not yet implemented");
    }

}
