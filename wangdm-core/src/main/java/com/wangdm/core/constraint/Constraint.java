package com.wangdm.core.constraint;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.constraint.CompareBean.ConditionType;

public abstract class Constraint implements Condition, Order ,Page {
    
    private static final Logger log = LoggerFactory.getLogger(Constraint.class);
    
    private Class<?> entityClass;
    
    private Integer currentPage = 0;
    
    private Integer pageSize = 0;
    
    private Long totalCount = (long) 0;
    
    private Map<String, Object> equalProperty = null;
    
    private Map<String, Object> nonProperty = null;
    
    private Map<String, String> likeProperty = null;
    
    private Map<String, CompareBean> conditionProperty = null;
    
    private Map<String, OrderType> orderProperty = null;
    
    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Map<String, Object> getEqualProperty(){
        return this.equalProperty;
    }
    
    public Map<String, Object> getNonProperty(){
        return this.nonProperty;
    }
    
    public Map<String, String> getLikeProperty(){
        return this.likeProperty;
    }
    
    public Map<String, CompareBean> getConditionProperty(){
        return this.conditionProperty;
    }
    
    public Map<String, OrderType> getOrderProperty(){
        return this.orderProperty;
    }

    @Override
    public Integer getPageSize() {
        
        return this.pageSize;
    }

    @Override
    public void setPageSize(Integer size) {
        
        this.pageSize = size;
        
    }

    @Override
    public Long getTotalCount() {

        return this.totalCount;
    }

    @Override
    public void setTotalCount(Long count) {

        this.totalCount = count;
        
    }

    @Override
    public Integer getCurrentPage() {

        return this.currentPage;
    }

    @Override
    public void setCurrentPage(Integer page) {

        this.currentPage = page;
        
    }

    @Override
    public Integer getTotalPage() {
        // TODO Auto-generated method stub
        return null;
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
