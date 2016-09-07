package com.wangdm.core;

import com.wangdm.core.dto.DemoDto;
import com.wangdm.core.entity.DemoEntity;
import com.wangdm.core.entity.TestEntity;

public final class App {
    
    public static void main( String[] args )
    {
        
        App app = new App();
        
        app.testToEntity();
        
        app.testFromEntity();
    }
    
    public void testToEntity(){
        
        DemoDto dto = new DemoDto();
        dto.setId(1);
        dto.setName("wangdm");
        dto.setTestId("15");
        DemoEntity entity = (DemoEntity)dto.toEntity(DemoEntity.class);
        if(entity != null){
            //System.out.println("id="+entity.getId()+"; name="+entity.getName()+"; test.Id="+entity.getTest().getId());
            System.out.println(entity.toString());
        }
    }
    
    public void testFromEntity(){
        
        DemoEntity entity = new DemoEntity();
        entity.setName("wangdm");
        
        TestEntity test = new TestEntity();
        entity.setTest(test);
        
        DemoDto dto = new DemoDto();
        dto.fromEntity(entity);
        System.out.println(dto.toString());
    }
}
