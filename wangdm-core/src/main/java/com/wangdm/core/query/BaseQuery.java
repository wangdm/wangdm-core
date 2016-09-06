package com.wangdm.core.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wangdm.core.constant.EntityStatus;
import com.wangdm.core.constraint.Order.OrderType;
import com.wangdm.core.constraint.Page;

public class BaseQuery implements Query, Page {

    
    private int page = 0;
    
    private int pageSize = 10;
    
    private long totalCount = (long) 0;
    
    private List<EntityStatus> status = null;
    
    private Map<String, OrderType> order = null;
    
    @Override
    public int getPageSize() {

        return this.pageSize;
    }

    @Override
    public void setPageSize(int size) {

        this.pageSize = size;
        
    }

    @Override
    public long getTotalCount() {

        return this.totalCount;
    }

    @Override
    public void setTotalCount(long count) {

        this.totalCount = count;
        
    }

    @Override
    public int getCurrentPage() {
        
        return this.page;
    }

    @Override
    public void setCurrentPage(int page) {

        this.page = page;
        
    }

    @Override
    public int getTotalPage() {
        
        long pages = this.totalCount/this.pageSize;
        long yun = this.totalCount%this.pageSize;
        if(yun==0){
            return (int)pages;
        }else{
            return (int) pages + 1;
        }
    }

    public List<EntityStatus> getStatus() {
        return status;
    }

    public void setStatus(List<EntityStatus> entityStatus) {
        this.status = entityStatus;
    }

    public void addStatus(EntityStatus status) {
        if(this.status == null){
            this.status = new ArrayList<EntityStatus>();
        }
        this.status.add(status);
    }

    public Map<String, OrderType> getOrder() {
        return order;
    }

    public void setOrder(Map<String, OrderType> order) {
        this.order = order;
    }

    public void addOrder(String order, OrderType type) {
        if(this.order==null){
            this.order = new HashMap<String, OrderType>();
        }
        this.order.put(order, type);
    }

}
