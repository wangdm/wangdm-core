package com.wangdm.core.constraint;

public interface Page {
    
    public Integer getPageSize();
    
    public void setPageSize(Integer size);
    
    public Long getTotalCount();
    
    public void setTotalCount(Long count);
    
    public Integer getCurrentPage();
    
    public void setCurrentPage(Integer page);
    
    public Integer getTotalPage();
}
