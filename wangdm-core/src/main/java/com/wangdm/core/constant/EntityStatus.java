package com.wangdm.core.constant;

import java.util.HashMap;
import java.util.Map;

public enum EntityStatus {
    UNAUTHORIZED(0,"未审核"),NORMAL(1,"正常"),FORBIDDEN(2,"禁止访问"),DELETE(3,"已删除");
    
    private Integer code;
    private String desc;
    
    private EntityStatus(Integer code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer toInteger(){
        return this.code;
    }
    
    public static EntityStatus valueOf(Integer code){
        switch(code){
        case 0:
            return EntityStatus.UNAUTHORIZED;
        case 1:
            return EntityStatus.NORMAL;
        case 2:
            return EntityStatus.FORBIDDEN;
        case 3:
            return EntityStatus.DELETE;
        }
        return null;
    }
    
    public static Map<String, String> map(){
        EntityStatus items[] = EntityStatus.values();
        Map<String, String> maps = new HashMap<String, String>(items.length);
        for(EntityStatus item : items){
            maps.put(item.toString(), item.desc);
        }
        return maps;
    }
}
