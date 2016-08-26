package com.wangdm.core.constraint;

public interface Page {
    
    public int getPageSize();
    
    public void setPageSize(int size);
    
    public long getTotalCount();
    
    public void setTotalCount(long count);
    
    public int getCurrentPage();
    
    public void setCurrentPage(int page);
    
    public int getTotalPage();
}
