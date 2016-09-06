package com.wangdm.core.dao.impl;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.wangdm.core.constraint.Condition;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.Page;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.entity.Entity;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<E extends Entity> implements BaseDao<E> {
    
    private static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

    private Class<E> clazz;
    
    public BaseDaoImpl(){
        String fullClassName = this.getClass().getName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        log.debug("this dao class name is "+ fullClassName);
        if(!"BaseDaoImpl".equals(className))
        {
            try{
                ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
                clazz = (Class<E>) type.getActualTypeArguments()[0];
                log.debug("this entity class name is "+ clazz.getName());
            }catch(ClassCastException e){
                log.error("Get parameterizedType failed in"+ fullClassName);
            }
        }
    }
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    
    protected Session getSession(){
        if(this.sessionFactory==null)
        {
            System.out.println("sessionFactory is null");
            return null;
        }
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable create(E entity){
        Session s = this.getSession();
        if(s==null){
            System.out.println("Session is null");
        }else{
            s.save(entity);
        }
        return s.getIdentifier(entity);
    }

    @Override
    public void update(E entity){
        this.getSession().update(entity);
    }
    
    @Override
    public void delete(Class<?> clazz, Serializable id){
        this.getSession().delete(this.findById(clazz, id));
    }
    
    @Override
    public E findById(Class<?> clazz, Serializable id){
        
        return (E)this.getSession().get(clazz, id);
    }

    @Override
    public List<E> listAll() {
        Session s = this.getSession();
        if(s!=null){
            Criteria c = s.createCriteria(this.clazz);
            c.setCacheable(true);
            ClassMetadata metadata = s.getSessionFactory().getClassMetadata(this.clazz);
            c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
            return c.list();
        }
        return null;
    }

    @Override
    public List<E> listAll(Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        
        c.setProjection(Projections.rowCount());
        Long rowCount = (Long)c.uniqueResult();
        if(rowCount!=null){
            page.setTotalCount(rowCount);
        }
        
        List<E> list = null;
        if(rowCount.intValue()>0){
            c.setProjection(null);
            ClassMetadata metadata = this.getSession().getSessionFactory().getClassMetadata(this.clazz);
            c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
            c.setMaxResults(page.getPageSize());
            c.setFirstResult(page.getCurrentPage()*page.getPageSize());
            list = c.list();
        }
        return list;
    }

    @Override
    public List<E> listAll(String order, Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        
        c.setProjection(Projections.rowCount());
        Long rowCount = (Long)c.uniqueResult();
        if(rowCount!=null){
            page.setTotalCount(rowCount);
        }
        
        List<E> list = null;
        if(rowCount.intValue()>0){
            c.setProjection(null);
            c.addOrder(Order.asc(order));
            c.setMaxResults(page.getPageSize());
            c.setFirstResult(page.getCurrentPage()*page.getPageSize());
            list = c.list();
        }
        return list;
    }

    /**
     * 
     * @param column
     * @param value
     */
    @Override
    public List<E> findByColumn(String column, Serializable value) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        if(value==null){
            c.add(Restrictions.isNull(column));
        }else{
            c.add(Restrictions.eq(column,value));
        }
        ClassMetadata metadata = this.getSession().getSessionFactory().getClassMetadata(this.clazz);
        c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
        return c.list();
    }
    
    /**
     * 
     * @param column
     * @param value
     */
    @Override
    public List<E> findByColumn(String column, Serializable value, Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        if(value==null){
            c.add(Restrictions.isNull(column));
        }else{
            c.add(Restrictions.eq(column,value));
        }
        ClassMetadata metadata = this.getSession().getSessionFactory().getClassMetadata(this.clazz);
        c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
        
        if(page!=null){
            c.setMaxResults(page.getPageSize());
            c.setFirstResult(page.getCurrentPage() * page.getPageSize());
        }
        return c.list();
    }

    @Override
    public List<E> findByConstraint(Constraint constraint) {

        Criteria c = this.getSession().createCriteria(constraint.getEntityClass());
        c.setCacheable(true);
        
        c = (Criteria) constraint.buildConstraint(c);
        
        return c.list();
    }
    
    @Override
    public List<E> findByCondition(Condition condition, Page page) {

        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        
        c = (Criteria) condition.buildConstraint(c);
        
        if(page!=null){
            c.setMaxResults(page.getPageSize());
            c.setFirstResult(page.getCurrentPage() * page.getPageSize());
        }
        return c.list();
    }

    @Override
    public Long countByCondition(Condition condition) {

        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        
        c = (Criteria) condition.buildConstraint(c);

        c.setProjection(Projections.rowCount());
        
        return (Long) c.uniqueResult();
    }

    @Override
    public Long countByColumn(String column, Serializable value) {
        
        Criteria c = this.getSession().createCriteria(this.clazz);
        c.setCacheable(true);
        
        if(value==null){
            c.add(Restrictions.isNull(column));
        }else{
            c.add(Restrictions.eq(column,value));
        }
        
        c.setProjection(Projections.rowCount());
        
        return (Long) c.uniqueResult();
    }
}
