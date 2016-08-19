package com.wangdm.core.query;

public interface Order {

    public enum OrderType{ASC,DESC};
    
    public void addOrder(String prop, OrderType type);
}
