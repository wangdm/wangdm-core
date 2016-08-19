package com.wangdm.core.query.hibernate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.query.ConditionBean;
import com.wangdm.core.query.ConditionBean.ConditionType;
import com.wangdm.core.query.QueryCondition;

public class HibernateQuery extends QueryCondition{
    
    private static final Logger log = LoggerFactory.getLogger(HibernateQuery.class);
    
    private Map<String, Object> equalProperty = null;
    
    private Map<String, Object> nonProperty = null;
    
    private Map<String, String> likeProperty = null;
    
    private Map<String, ConditionBean> conditionProperty = null;
    
    private Map<String, OrderType> orderProperty = null;
    
    public Map<String, Object> getEqualProperty(){
        return this.equalProperty;
    }
    
    public Map<String, Object> getNonProperty(){
        return this.nonProperty;
    }
    
    public Map<String, String> getLikeProperty(){
        return this.likeProperty;
    }
    
    public Map<String, ConditionBean> getConditionProperty(){
        return this.conditionProperty;
    }
    
    public Map<String, OrderType> getOrderProperty(){
        return this.orderProperty;
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
            conditionProperty = new HashMap<String, ConditionBean>();
        }
        ConditionBean bean = new ConditionBean(ConditionType.GE, value);
        conditionProperty.put(prop, bean);
        log.debug("add query condition property("+prop+") > value("+value+")");
    }

    @Override
    public void addLessCondition(String prop, Object value) {
        if(conditionProperty == null){
            conditionProperty = new HashMap<String, ConditionBean>();
        }
        ConditionBean bean = new ConditionBean(ConditionType.LE, value);
        conditionProperty.put(prop, bean);
        log.debug("add query condition property("+prop+") < value("+value+")");
    }

    @Override
    public void addBetweenCondition(String prop, Object min, Object max) {
        if(conditionProperty == null){
            conditionProperty = new HashMap<String, ConditionBean>();
        }
        ConditionBean bean = new ConditionBean(max, min);
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

    @Override
    public Object buildConstraint(Object constraint) {
        if(constraint instanceof Criteria){
            return buildCriteriaConstraint((Criteria)constraint);
        }else{
            log.error("Unknow Constraint");
        }
        return null;
    }
    
    private Criteria buildCriteriaConstraint(Criteria c){
        
        Map<String,Object> equalColumn = this.getEqualProperty();
        if(equalColumn!=null){
            Set<String> keySet = equalColumn.keySet();
            for(String key : keySet){
                Object object = equalColumn.get(key);
                if(object == null){
                    c.add(Restrictions.isNull(key));
                }else if(object instanceof Collection){
                    c.add(Restrictions.in(key, (Collection<?>)object));
                }else{
                    c.add(Restrictions.eq(key, equalColumn.get(key)));
                }
            }
        }
        
        Map<String,Object> nonColumn = this.getNonProperty();
        if(nonColumn!=null){
            Set<String> keySet = nonColumn.keySet();
            for(String key : keySet){
                Object object = nonColumn.get(key);
                if(object == null){
                    c.add(Restrictions.isNotNull(key));
                }else if(object instanceof Collection){
                    c.add(Restrictions.not(Restrictions.in(key, (Collection<?>)object)));
                }else{
                    c.add(Restrictions.not(Restrictions.eq(key, nonColumn.get(key))));
                }
            }
        }
        
        Map<String,ConditionBean> conditionColumn = this.getConditionProperty();
        if(conditionColumn!=null){
            Set<String> keySet = nonColumn.keySet();
            for(String key : keySet){
                ConditionBean bean = conditionColumn.get(key);
                switch(bean.getType()){
                    case GT:
                        c.add(Restrictions.gt(key, bean.getValue()));
                        break;
                    case LT:
                        c.add(Restrictions.lt(key, bean.getValue()));
                        break;
                    case GE:
                        c.add(Restrictions.ge(key, bean.getValue()));
                        break;
                    case LE:
                        c.add(Restrictions.le(key, bean.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
        
        Map<String,String> searchColumn = this.getLikeProperty();
        if(searchColumn!=null){
            Set<String> keySet = searchColumn.keySet();
            for(String key : keySet){
                c.add(Restrictions.like(key, searchColumn.get(key), MatchMode.ANYWHERE));
            }
        }
        
        Map<String, OrderType> orderColumn = this.getOrderProperty();
        if(orderColumn!=null){
            Set<String> keySet = orderColumn.keySet();
            for(String key : keySet){
                OrderType type = orderColumn.get(key);
                if(type==OrderType.ASC){
                    c.addOrder(Order.asc(key));
                }else if(type==OrderType.DESC){
                    c.addOrder(Order.desc(key));
                }
            }
        }
        return c;
    }

}
