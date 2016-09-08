package com.wangdm.user.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.dto.RolePermissionDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class RoleServiceTest {
    
    @Autowired
    RoleService roleService;

    @Test
    public void testFindById() {
        fail("Not yet implemented");
    }

    @Test
    public void testQuery() {
        BaseQuery query = new BaseQuery();
        List<Dto> dtoList = roleService.query(query);
        for(Dto dto : dtoList){
            System.out.println(dto.toString());
        }
    }

    @Test
    public void testListPermission() {
        List<PermissionDto> perms =  roleService.listPermission(1L);
        
        for(PermissionDto perm : perms){
            System.out.println(perm.toString());
        }
    }

    @Test
    public void testAssignPermissionRolePermissionDto() {
        
        RolePermissionDto perm = new RolePermissionDto();
        
        perm.setRoleId("1");
        perm.setPermId("1");
        roleService.assignPermission(perm);
        
        perm.setRoleId("1");
        perm.setPermId("2");
        roleService.assignPermission(perm);
    }

    @Test
    public void testAssignPermissionListOfRolePermissionDto() {
        
        List<RolePermissionDto> perms = new ArrayList<RolePermissionDto>();
        
        RolePermissionDto perm = null;

        perm = new RolePermissionDto();
        perm.setRoleId("1");
        perm.setPermId("3");
        perms.add(perm);

        perm = new RolePermissionDto();
        perm.setRoleId("1");
        perm.setPermId("4");
        perms.add(perm);
        
        roleService.assignPermission(perms);
    }

    @Test
    public void testRemovePermissionLongPermissionDto() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemovePermissionLongListOfPermissionDto() {
        fail("Not yet implemented");
    }

    @Test
    public void testResetPermission() {
        fail("Not yet implemented");
    }

    @Test
    public void testCreate() {
        RoleDto dto = new RoleDto();
        
        dto.setName("admin");
        dto.setTitle("Administrator");
        roleService.create(dto);
        
        dto.setName("teacher");
        dto.setTitle("Teacher");
        roleService.create(dto);
        
        dto.setName("student");
        dto.setTitle("Student");
        roleService.create(dto);
        
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
