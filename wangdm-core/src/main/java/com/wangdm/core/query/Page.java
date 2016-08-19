package com.wangdm.core.query;

public interface Page {
    
    
    
    public Integer getPageSize();
    
    public Integer getTotalPage();
    
    public Integer getTotalCount();
    
    public void setTotalCount(Integer count);
    
    public Integer getCurrentPage();
}
