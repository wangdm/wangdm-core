package com.wangdm.user.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.core.dto.Dto;
import com.wangdm.user.dto.GroupDto;
import com.wangdm.user.query.GroupQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class GroupServiceTest {
    
    @Autowired
    GroupService groupService;

    @Test
    public void testFindById() {
        Dto dto = groupService.findById(1L);
        System.out.println(dto.toString());
    }

    @Test
    public void testQuery() {
        GroupQuery query = new GroupQuery();
        List<Dto> groupList = groupService.query(query);
        for(Dto dto : groupList){
            System.out.println(dto.toString());
        }
    }

    @Test
    public void testCreate() {
        GroupDto dto = null;
        
        dto = new GroupDto();
        dto.setName("普教课程");
        groupService.create(dto);
        
        dto = new GroupDto();
        dto.setName("高校课程");
        groupService.create(dto);
        
        dto = new GroupDto();
        dto.setName("兴趣爱好");
        groupService.create(dto);
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
