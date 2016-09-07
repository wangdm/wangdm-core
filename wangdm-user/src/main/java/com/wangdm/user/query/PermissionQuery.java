package com.wangdm.user.query;

import com.wangdm.core.query.BaseQuery;

public class PermissionQuery extends BaseQuery {

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
