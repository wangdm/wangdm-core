package com.wangdm.core.hibernate;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangdm.core.constraint.Condition;
import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.entity.Entity;

@SuppressWarnings("unchecked")
public class BaseDao<E extends Entity> implements Dao<E> {
    
    private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

    private Class<E> clazz;

    @Autowired
    private SessionFactory sessionFactory;
    
    
    public BaseDao(){
        String fullClassName = this.getClass().getName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.')+1);
        log.error("this dao class name is "+ fullClassName);
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
    
    
    protected Session getSession(){
        if(this.sessionFactory==null)
        {
            log.error("sessionFactory is null");
            return null;
        }
        return this.sessionFactory.getCurrentSession();
    }

    
    @Override
    public Class<E> getClazz() {
        return clazz;
    }

    
    @Override
    public void setClazz(Class<E> clazz) {
        this.clazz = clazz;
    }

    
    @Override
    public Serializable create(E entity){
        
        Session s = this.getSession();
        if(s==null){
            log.error("Create entity failed, Get Session failed!");
            return null;
        }else{
            s.save(entity);
        }
        return s.getIdentifier(entity);
    }

    
    @Override
    public void update(E entity){
        Session s = this.getSession();
        if(s==null){
            log.error("Update entity failed, Get Session failed!");
        }else{
            s.update(entity);
        }
    }

    
    @Override
    public void delete(E entity) {
        Session s = this.getSession();
        if(s==null){
            log.error("Delete entity failed, Get Session failed!");
        }else{
            s.delete(entity);
        }
    }
    
    
    @Override
    public void deleteById(Serializable id){
        
        if(clazz != null){
            Session s = this.getSession();
            if(s==null){
                log.error("Delete entity failed, Get Session failed!");
            }else{
                E entity = this.findById(clazz, id);
                if(entity != null){
                    s.delete(entity);
                }else{
                    log.warn("Delete entity failed, No such entity[id="+id+"]!");
                }
            }
        }else{
            log.error("You must set the clazz first!");
        }
    }
    
    
    @Override
    public void deleteById(Class<?> clazz, Serializable id){

        Session s = this.getSession();
        if(s==null){
            log.error("Delete entity failed, Get Session failed!");
        }else{
            E entity = this.findById(clazz, id);
            if(entity != null){
                s.delete(entity);
            }else{
                log.warn("Delete entity failed, No such entity[id="+id+"]!");
            }
        }
    }

    
    @Override
    public void deleteByColumn(String column, Object value) {
        // TODO Auto-generated method stub
        
    }
    
    
    @Override
    public E findById(Serializable id){
        
        if(clazz != null){
            Session s = this.getSession();
            if(s==null){
                log.error("Find entity failed, Get Session failed!");
            }else{
                return (E) s.get(clazz, id);
            }
        }else{
            log.error("You must set the clazz first!");
        }
        
        return null;
    }
    
    
    @Override
    public E findById(Class<?> clazz, Serializable id){
        
        Session s = this.getSession();
        if(s==null){
            log.error("Find entity failed, Get Session failed!");
        }else{
            return (E) s.get(clazz, id);
        }
        
        return null;
    }

    
    @Override
    public List<E> listAll() {
        Session s = this.getSession();
        if(s != null){
            Criteria c = s.createCriteria(this.clazz);
            c.setCacheable(true);
            ClassMetadata metadata = s.getSessionFactory().getClassMetadata(this.clazz);
            c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
            return c.list();
        }
        
        return null;
    }

    
    /**
     * 
     * @param column
     * @param value
     */
    @Override
    public List<E> findByColumn(String column, Object value) {
        Session s = this.getSession();
        if(s != null){
            Criteria c = s.createCriteria(this.clazz);
            c.setCacheable(true);
            if(value == null){
                c.add(Restrictions.isNull(column));
            }
            else if(value instanceof Collection){
                c.add(Restrictions.in(column, (Collection<?>)value));
            }
            else{
                c.add(Restrictions.eq(column,value));
            }
            ClassMetadata metadata = this.getSession().getSessionFactory().getClassMetadata(this.clazz);
            c.addOrder(Order.asc(metadata.getIdentifierPropertyName()));
            return c.list();
        }
        
        return null;
    }

    
    @Override
    public List<E> findByCondition(Condition condition) {
        // TODO Auto-generated method stub
        return null;
    }

    
    @Override
    public List<E> findByConstraint(Constraint constraint) {

        Criteria c = this.getSession().createCriteria(constraint.getEntityClass());
        c.setCacheable(true);
        
        c = (Criteria) constraint.buildConstraint(c);
        
        return c.list();
    }
    
}
