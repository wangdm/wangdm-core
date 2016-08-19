package com.wangdm.user.constant;

public enum RoleType {
    SYSTEM(0),USER(1);
    
    private int code;
    
    private RoleType(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
