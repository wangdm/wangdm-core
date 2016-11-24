package com.wangdm.ui.query;

import com.wangdm.core.query.BaseQuery;
import com.wangdm.ui.constant.MenuType;

public class MenuQuery extends BaseQuery {

    private Long parentId = null;
    
    private MenuType type = MenuType.NAVIGATION;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }
}
