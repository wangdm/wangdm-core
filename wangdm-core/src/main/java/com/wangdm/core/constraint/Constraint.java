package com.wangdm.core.constraint;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.constraint.CompareBean.ConditionType;

public abstract class Constraint implements Condition, Order ,Page {
    
    private static final Logger log = LoggerFactory.getLogger(Constraint.class);
    
    private Class<?> entityClass = null;
    
    private int currentPage = 0;
    
    private int pageSize = 0;
    
    private long totalCount = (long) 0;
    
    private Map<String, Object> equalProperty = null;
    
    private Map<String, Object> nonProperty = null;
    
    private Map<String, String> likeProperty = null;
    
    private Map<String, CompareBean> conditionProperty = null;
    
    private Map<String, OrderType> orderProperty = null;
    
    public void clear(){
        
        entityClass = null;
        currentPage = 0;
        pageSize = 0;
        totalCount = (long) 0;
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

    public Map<String, OrderType> getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(Map<String, OrderType> orderProperty) {
        this.orderProperty = orderProperty;
    }

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

        return this.currentPage;
    }

    @Override
    public void setCurrentPage(int page) {

        this.currentPage = page;
        
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
    
    @Override
    public void addOrder(String prop, OrderType type) {
        if(orderProperty == null){
            orderProperty = new HashMap<String, OrderType>();
        }
        orderProperty.put(prop, type);
        log.debug("add order condition property("+prop+") = value("+type+")");
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
}
