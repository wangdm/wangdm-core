package com.wangdm.base.constant;

public enum EntityStatus {
    UNAURHORIZED(0),FORBIDDEN(1),BLOCKED(2),DELETE(3);
    
    private int code;
    
    private EntityStatus(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
