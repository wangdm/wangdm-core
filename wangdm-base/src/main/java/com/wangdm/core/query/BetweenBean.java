package com.wangdm.core.query;

public final class BetweenBean {
    
    private Object maxValue;
    
    private Object minValue;
    
    public BetweenBean(){}
    
    public BetweenBean(Object max, Object min){
        this.maxValue = max;
        this.minValue = min;
    }
    
    public Object getMaxValue(){
        return this.maxValue;
    }
    
    public void setMaxValue(Object max){
        this.maxValue = max;
    }
    
    public Object getMinValue(){
        return this.minValue;
    }
    
    public void setMinValue(Object min){
        this.minValue = min;
    }

}
