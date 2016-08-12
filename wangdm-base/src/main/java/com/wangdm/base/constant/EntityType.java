package com.wangdm.base.constant;

public enum EntityType {
    USER(0),ROLE(1),USERROLE(2),GROUP(3),GROUPROLE(4),
    PERMISSION(5),ROLEPERMISSION(6),
    LOGINRECORD(20),ACTIONRECORD(21),LOGRECORD(22);
    
    private int code;
    
    private EntityType(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
