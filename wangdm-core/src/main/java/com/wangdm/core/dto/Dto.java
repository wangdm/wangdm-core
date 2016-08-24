package com.wangdm.core.dto;

import com.wangdm.core.entity.Entity;

public interface Dto {
    
    public Entity toEntity(Class<?> clazz);
    
    public void fromEntity(Entity entity);

}
