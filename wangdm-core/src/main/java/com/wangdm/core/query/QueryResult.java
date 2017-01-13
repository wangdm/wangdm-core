package com.wangdm.core.query;

import java.util.List;

import com.wangdm.core.dto.Dto;

public class QueryResult {
    
    private int page;
    
    private int size;
    
    private long amount;
    
    private List<Dto> dtoList;
    
    
    public QueryResult(int page, int size, long amount){
        this.page = page;
        this.size = size;
        this.amount = amount;
    }
    
    
    public QueryResult(int page, int size, long amount, List<Dto> dtoList){
        this.page = page;
        this.size = size;
        this.amount = amount;
        this.dtoList = dtoList;
    }
    

    public List<Dto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<Dto> dtoList) {
        this.dtoList = dtoList;
    }

    public int getCurrentPage() {
        return page;
    }

    public void setCurrentPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return size;
    }

    public void setPageSize(int size) {
        this.size = size;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTotalPage() {
        return (int) (amount/size);
    }

    public int getCurrentSize() {
        
        if(dtoList!=null){
            return dtoList.size();
        }
        return 0;
    }

}
