package com.wangdm.base.utils;

import java.util.Map;

/**
 * @author wangdm
 * @version 1.0
 * @created 2016.06.29
 * @desc 查询条件实体
 */
public class QueryBean {
    
    public enum OrderType{ASC,DESC};
    
    /**
     * @author wangdm
     * @version 1.0
     * @created 2016.06.29
     * @desc 条件匹配属性
     */
    private Map<String, Object> conditionProperty;
    
    /**
     * @author wangdm
     * @version 1.0
     * @created 2016.06.29
     * @desc 模糊匹配属性
     */
    private Map<String, String> searchProperty;
    
    /**
     * @author wangdm
     * @version 1.0
     * @created 2016.06.29
     * @desc 排序字段
     */
    private Map<String, OrderType> order;

    public Map<String, Object> getConditionProperty() {
        return conditionProperty;
    }

    public void setConditionProperty(Map<String, Object> conditionProperty) {
        this.conditionProperty = conditionProperty;
    }

    public Map<String, String> getSearchProperty() {
        return searchProperty;
    }

    public void setSearchProperty(Map<String, String> searchProperty) {
        this.searchProperty = searchProperty;
    }

    public Map<String, OrderType> getOrder() {
        return order;
    }

    public void setOrder(Map<String, OrderType> order) {
        this.order = order;
    }
    
}
