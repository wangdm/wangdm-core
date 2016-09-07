package com.wangdm.user.query;

import com.wangdm.core.query.BaseQuery;

public class GroupQuery extends BaseQuery {

    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
