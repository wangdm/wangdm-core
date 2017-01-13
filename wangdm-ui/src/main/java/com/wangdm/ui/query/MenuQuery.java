package com.wangdm.ui.query;

import com.wangdm.core.query.PageQuery;
import com.wangdm.ui.constant.MenuType;

public class MenuQuery extends PageQuery {

    private Long parentId = null;
    
    private Boolean display = null;
    
    private MenuType type = MenuType.NAVIGATION;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }
}
