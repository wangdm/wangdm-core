package com.wangdm.core.query;

public final class ConditionBean {
    
    public enum ConditionType{GT,LT,GE,LE,BETWEEN};
    
    private ConditionType type;
    
    private Object value;
    
    private Object maxValue;
    
    private Object minValue;
    
    public ConditionBean(){}
    
    public ConditionBean(ConditionType type, Object value){
        this.type = type;
        this.value = value;
    }
    
    public ConditionBean(Object max, Object min){
        this.type = ConditionType.BETWEEN;
        this.maxValue = max;
        this.minValue = min;
    }
    
    public ConditionType getType(){
        return this.type;
    }
    
    public void setType(ConditionType type){
        this.type = type;
    }
    
    public Object getValue(){
        return this.value;
    }
    
    public void setValue(Object value){
        this.value = value;
    }
    
    public Object getMaxValue(){
        return this.maxValue;
    }
    
    public void setMaxValue(Object maxValue){
        this.maxValue = maxValue;
    }
    
    public Object getMinValue(){
        return this.minValue;
    }
    
    public void setMinValue(Object minValue){
        this.minValue = minValue;
    }
}
