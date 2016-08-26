package com.wangdm.core.query;

public class BaseQuery implements Query {
    
    private int page;

    @Override
    public int getCurrentPage() {
        return this.page;
    }

    @Override
    public void setCurrentPage(int page) {
        this.page = page;
    }

}
