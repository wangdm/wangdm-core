package com.wangdm.user.constant;

public final class PermissionAction {
    
    public static final int ADD = 1;
    
    public static final int MODIFY = 2;
    
    public static final int DELETE = 4;
    
    public static final int VIEW = 8;
    
    public static final int LIST = 16;
    
    public static final int ERASE = 32;
    
    public static final int READ = VIEW | LIST;
    
    public static final int WRITE = ADD | MODIFY;
    
    public static final int EDIT = READ | WRITE;
    
    public static final int ALL = ADD | MODIFY | DELETE | VIEW | LIST | ERASE;
    
}
