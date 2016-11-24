package com.wangdm.core.hibernate;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangdm.core.constraint.CompareBean;
import com.wangdm.core.constraint.Constraint;

public class HibernateConstraint extends Constraint{
    
    private static final Logger log = LoggerFactory.getLogger(HibernateConstraint.class);
    
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
        
        Map<String,CompareBean> conditionColumn = this.getConditionProperty();
        if(conditionColumn!=null){
            Set<String> keySet = conditionColumn.keySet();
            for(String key : keySet){
                CompareBean bean = conditionColumn.get(key);
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
