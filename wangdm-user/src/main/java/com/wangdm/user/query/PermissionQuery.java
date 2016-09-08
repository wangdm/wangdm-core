package com.wangdm.user.query;

import com.wangdm.core.query.BaseQuery;

public class PermissionQuery extends BaseQuery {

    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
