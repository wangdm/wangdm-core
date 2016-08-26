package com.wangdm.ui.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangdm.core.dto.Dto;
import com.wangdm.ui.dto.HotspotDto;
import com.wangdm.ui.dto.HotspotShowDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-*.xml")
public class HotspotServiceTest {
    
    @Autowired
    HotspotService hotspotService;
    
    @Test
    public void testCreate() {
        HotspotDto dto = new HotspotDto();
        dto.setTitle("fdsfs");
        dto.setImage("fsfsaf.png");
        dto.setUrl("http://fsfsdfsdf.com/fsdfsdfds");
        hotspotService.create(dto);
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
        List<Dto> dtoList = hotspotService.query(null);
        if(dtoList!=null && dtoList.size()>0){
            for(Dto dto : dtoList){
                System.out.println(dto.toString());
            }
        }
    }

    @Test
    public void testShowHotspot() {
        List<HotspotShowDto> dtoList = hotspotService.showHotspot(5);
        if(dtoList!=null && dtoList.size()>0){
            for(HotspotShowDto dto : dtoList){
                System.out.println(dto.toString());
            }
        }
    }

}
