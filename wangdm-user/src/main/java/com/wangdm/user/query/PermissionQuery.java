package com.wangdm.user.query;

import com.wangdm.core.query.PageQuery;

public class PermissionQuery extends PageQuery {
    
    private Long group;
    
    public Long getGroup() {
        return group;
    }
    
    public void setGroup(Long group) {
        this.group = group;
    }
    
}
