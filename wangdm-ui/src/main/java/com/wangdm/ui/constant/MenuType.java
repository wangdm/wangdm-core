package com.wangdm.ui.constant;

public enum MenuType {

    MENU(0),NAVIGATION(1);
    
    private int code;
    
    private MenuType(int code)
    {
        this.code = code;
    }
    
    public int toInt(){
        return this.code;
    }
    
    public static MenuType valueOf(int code){
        switch(code){
        case 0:
            return MenuType.MENU;
        case 1:
            return MenuType.NAVIGATION;
        }
        return null;
    }
}
