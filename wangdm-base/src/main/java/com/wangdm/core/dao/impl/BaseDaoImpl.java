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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.query.Condition;
import com.wangdm.core.query.Page;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private Class<T> clazz;
    
    public BaseDaoImpl(){
        String fullClassName = this.getClass().getName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        if(!"BaseDaoImpl".equals(className))
        {
            //System.out.println("this class name is "+ className);
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();  
            clazz = (Class<T>) type.getActualTypeArguments()[0];
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
    public Serializable create(T entity){
        Session s = this.getSession();
        if(s==null){
            System.out.println("Session is null");
        }else{
            s.save(entity);
        }
        return s.getIdentifier(s);
    }

    @Override
    public void update(T entity){
        this.getSession().update(entity);
    }
    
    @Override
    public void delete(Serializable id){
        this.getSession().delete(this.findById(id));
    }
    
    @Override
    public T findById(Serializable id){
        return (T)this.getSession().get(this.clazz, id);
    }

    @Override
    public List<T> listAll() {
        Session s = this.getSession();
        if(s!=null){
            Criteria c = s.createCriteria(this.clazz);
            ClassMetadata metadata = s.getSessionFactory().getClassMetadata(this.clazz);
            c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
            return c.list();
        }
        return null;
    }

    @Override
    public List<T> listAll(Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        
        c.setProjection(Projections.rowCount());
        Long rowCount = (Long)c.uniqueResult();
        int count = 0;
        if(rowCount!=null){
            count = rowCount.intValue();
        }
        page.setTotalCount(Integer.valueOf(count));
        
        List<T> list = null;
        if(count>0){
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
    public List<T> listAll(String order, Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
        
        c.setProjection(Projections.rowCount());
        Long rowCount = (Long)c.uniqueResult();
        int count = 0;
        if(rowCount!=null){
            count = rowCount.intValue();
        }
        page.setTotalCount(count);
        
        List<T> list = null;
        if(count>0){
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
    public List<T> findByColumn(String column, Serializable value) {
        Criteria c = this.getSession().createCriteria(this.clazz);
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
    public List<T> findByColumn(String column, Serializable value, Page page) {
        Criteria c = this.getSession().createCriteria(this.clazz);
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
    public List<T> findByCondition(Condition condition, Page page) {

        Criteria c = this.getSession().createCriteria(this.clazz);
        
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
        
        c = (Criteria) condition.buildConstraint(c);

        c.setProjection(Projections.rowCount());
        
        return (Long) c.uniqueResult();
    }

    @Override
    public Long countByColumn(String column, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }
}
