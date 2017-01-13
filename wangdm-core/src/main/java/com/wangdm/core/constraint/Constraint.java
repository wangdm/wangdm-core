package com.wangdm.core.constraint;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.constant.OrderType;
import com.wangdm.core.constraint.CompareBean.ConditionType;

public abstract class Constraint implements Condition {
    
    private static final Logger log = LoggerFactory.getLogger(Constraint.class);
    
    private Class<?> entityClass = null;
    
    private Map<String, Object> equalProperty = null;
    
    private Map<String, Object> nonProperty = null;
    
    private Map<String, String> likeProperty = null;
    
    private Map<String, CompareBean> conditionProperty = null;
    
    private Integer page = 0;
    
    private Integer size = 20;
    
    private Long amount = (long) 0;
    
    private String orderProperty = null;
    
    private OrderType orderType = OrderType.ASC;
    
    public void clear(){
        
        entityClass = null;
        page = 0;
        size = 20;
        amount = (long) 0;
        equalProperty = null;
        nonProperty = null;
        likeProperty = null;
        conditionProperty = null;
        orderProperty = null;
        
    }
    
    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Map<String, Object> getEqualProperty() {
        return equalProperty;
    }

    public void setEqualProperty(Map<String, Object> equalProperty) {
        this.equalProperty = equalProperty;
    }

    public Map<String, Object> getNonProperty() {
        return nonProperty;
    }

    public void setNonProperty(Map<String, Object> nonProperty) {
        this.nonProperty = nonProperty;
    }

    public Map<String, String> getLikeProperty() {
        return likeProperty;
    }

    public void setLikeProperty(Map<String, String> likeProperty) {
        this.likeProperty = likeProperty;
    }

    public Map<String, CompareBean> getConditionProperty() {
        return conditionProperty;
    }

    public void setConditionProperty(Map<String, CompareBean> conditionProperty) {
        this.conditionProperty = conditionProperty;
    }

    @Override
    public void addEqualCondition(String prop, Object value) {
        if(equalProperty == null){
            equalProperty = new HashMap<String, Object>();
        }
        equalProperty.put(prop, value);
        log.debug("add query condition property("+prop+") = value("+value+")");
    }

    @Override
    public void addNonCondition(String prop, Object value) {
        if(nonProperty == null){
            nonProperty = new HashMap<String, Object>();
        }
        nonProperty.put(prop, value);
        log.debug("add query condition property("+prop+") != value("+value+")");
    }

    @Override
    public void addGreaterCondition(String prop, Object value) {
        if(conditionProperty == null){
            conditionProperty = new HashMap<String, CompareBean>();
        }
        CompareBean bean = new CompareBean(ConditionType.GE, value);
        conditionProperty.put(prop, bean);
        log.debug("add query condition property("+prop+") > value("+value+")");
    }

    @Override
    public void addLessCondition(String prop, Object value) {
        if(conditionProperty == null){
            conditionProperty = new HashMap<String, CompareBean>();
        }
        CompareBean bean = new CompareBean(ConditionType.LE, value);
        conditionProperty.put(prop, bean);
        log.debug("add query condition property("+prop+") < value("+value+")");
    }

    @Override
    public void addBetweenCondition(String prop, Object min, Object max) {
        if(conditionProperty == null){
            conditionProperty = new HashMap<String, CompareBean>();
        }
        CompareBean bean = new CompareBean(max, min);
        conditionProperty.put(prop, bean);
        log.debug("add query condition property("+prop+") between min("+min+") and max("+ max+")");
    }

    @Override
    public void addLikeCondition(String prop, String value) {
        if(likeProperty == null){
            likeProperty = new HashMap<String, String>();
        }
        likeProperty.put(prop, value);
        log.debug("add query condition property("+prop+") like value("+value+")");
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
}
