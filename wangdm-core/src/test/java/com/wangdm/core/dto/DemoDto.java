package com.wangdm.core.dto;

import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.core.entity.DemoEntity;
import com.wangdm.core.entity.Entity;

public class DemoDto extends BaseDto {

    @DtoMapper(entity = DemoEntity.class, field = "id")
    private int id;

    @DtoMapper(entity = DemoEntity.class, field = "test.id")
    private String testId;
    
    @DtoMapper(entity = DemoEntity.class, field = "name")
    private String name;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public Entity toEntity(Class<?> clazz) {
        /*
        Entity entity = null;
        try {
            entity = (Entity) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(entity instanceof DemoEntity){
            DemoEntity demoEntity = (DemoEntity)entity;
            demoEntity.setId(Long.valueOf(this.getId()));
            demoEntity.setName(this.name);
        }else{
            //return null;
        }
        return entity;
        //*/
        return super.toEntity(clazz);
    }

    @Override
    public void fromEntity(Entity entity) {
        /*
        if(entity instanceof DemoEntity){
            DemoEntity demoEntity = (DemoEntity)entity;
            this.id = demoEntity.getId().toString();
            this.name = demoEntity.getName();
        }else{
            //return null;
        }
        //*/
        super.fromEntity(entity);

    }

    @Override
    public Long getEntityId() {
        // TODO Auto-generated method stub
        return null;
    }
}
