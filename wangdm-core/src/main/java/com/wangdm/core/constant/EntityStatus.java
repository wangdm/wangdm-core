package com.wangdm.core.constant;

public enum EntityStatus {
    UNAURHORIZED(0),NORMAL(1),FORBIDDEN(2),BLOCKED(3),DELETE(4);
    
    private int code;
    
    private EntityStatus(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
