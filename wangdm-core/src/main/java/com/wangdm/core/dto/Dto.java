package com.wangdm.core.dto;

import com.wangdm.core.entity.Entity;

public interface Dto {
    
    public Long getEntityId();
    
    public Entity toEntity(Class<?> clazz);

    public Entity toEntity(Entity entity);
    
    public void fromEntity(Entity entity);

}
