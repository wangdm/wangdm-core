package com.wangdm.user.constant;

public enum Gender {
    SECRET(0),MALE(1),FEMALE(2);
    
    private int code;
    
    private Gender(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
}
