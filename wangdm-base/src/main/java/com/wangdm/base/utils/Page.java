package com.wangdm.base.utils;

public class Page {
    
    private int currentPage;//当前页
    
    private int pageSize;//每页行数
    
    private long totalCount;//总行数
    
    private long totalPage;//总页数

    public Page() {
        this.currentPage = 0;
        this.pageSize = 10;
        this.totalCount = 0;
        this.totalPage = 0;
    }
    
    public Page(int pageSize) {
        this.currentPage = 0;
        this.pageSize = pageSize;
        this.totalCount = 0;
        this.totalPage = 0;
    }

    public Page(int currentPage, int pageSize, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        int tmp = totalCount % pageSize;
        if(tmp==0){
            this.totalPage = totalCount / pageSize;
        }else{
            this.totalPage = totalCount / pageSize + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        long tmp = (this.totalCount % pageSize);
        if(tmp==0){
            this.totalPage = this.totalCount / pageSize;
        }else{
            this.totalPage = this.totalCount / pageSize + 1;
        }
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long count) {
        this.totalCount = count;
        long tmp = count % this.pageSize;
        if(tmp==0){
            this.totalPage = count / this.pageSize;
        }else{
            this.totalPage = count / this.pageSize + 1;
        }
    }

    public long getTotalPage() {
        return totalPage;
    }

//  public void setTotalPage(int totalPage) {
//      this.totalPage = totalPage;
//  }
    
}

    
    
