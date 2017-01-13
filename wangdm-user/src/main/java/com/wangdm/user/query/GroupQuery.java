package com.wangdm.user.query;

import com.wangdm.core.query.PageQuery;

public class GroupQuery extends PageQuery {
    
    private Long parent;

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
    
    
}
