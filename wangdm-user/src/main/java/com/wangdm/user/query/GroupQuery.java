package com.wangdm.user.query;

import com.wangdm.core.query.BaseQuery;

public class GroupQuery extends BaseQuery {

    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
