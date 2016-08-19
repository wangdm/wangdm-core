package com.wangdm.user.constant;

public enum PermissionAction {
    ADD(0),MODIFY(1),DELETE(2),VIEW(3),LIST(4);
    
    private int code;
    
    private PermissionAction(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
