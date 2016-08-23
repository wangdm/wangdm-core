package com.wangdm.core.constraint;

public interface Order {

    public enum OrderType{ASC,DESC};
    
    public void addOrder(String prop, OrderType type);
}
