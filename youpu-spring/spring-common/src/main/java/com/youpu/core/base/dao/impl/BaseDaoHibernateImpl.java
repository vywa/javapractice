package com.youpu.core.base.dao.impl;

import com.youpu.core.base.dao.BaseDaoHibernate;
import com.youpu.core.page.PageList;
import com.youpu.core.page.impl.PageListImpl;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class BaseDaoHibernateImpl implements BaseDaoHibernate {


    private static final Logger logger = LoggerFactory.getLogger(BaseDaoHibernateImpl.class);

    @Resource
    SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public Session getNewSession(){return sessionFactory.openSession();}

    public void flush(){getSession().flush();}

    public void clear(){getSession().clear();}



    @Override
    public void delete(Object object) {
        getSession().delete(object);
    }

    @Override
    public void deleteAll(Collection collection) {
        for(Object object: collection){
            getSession().delete(object);
        }
    }

    @Override
    public void deleteById(Class clazz, Serializable id) {
        removeObject(clazz,id);
    }

    @Override
    public List getObjects(Class clazz) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(clazz);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    @Override
    public Object getObject(Class clazz, Serializable id) {
        return getSession().get(clazz,id);
    }

    @Override
    public void saveObject(Object object) {
        getSession().saveOrUpdate(object);
    }

    @Override
    public void removeObject(Class clazz, Serializable id) {
        getSession().delete(getObject(clazz,id));
    }

    @Override
    public void removeObject(Object object) {
        getSession().delete(object);
    }

    @Override
    public Object createObject(Object object) {
        return getSession().merge(object);
    }

    @Override
    public List find(String arg) {
        Query query = getSession().createQuery(arg);
        return query.list();
    }

    @Override
    public List find(String arg, Object object) {
        return find(arg,new Object[]{object});
    }

    @Override
    public List find(String arg, Object[] objects) {
        Query query = getSession().createQuery(arg);
        if(objects!=null){
            for(int i=0;i<objects.length;i++){
                query.setParameter(i,objects[i]);
            }
        }
        return query.list();
    }

    @Override
    public PageList getPageListByCriteria(DetachedCriteria detachedCriteria,int currentNo, int pageSize) {

        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
        List orderEntry = new ArrayList();

        Field field = null;
        try{
            field = CriteriaImpl.class.getDeclaredField("orderEntry");
            field.setAccessible(true);
            orderEntry = (List) field.get(criteriaImpl);
            field.set(criteria,new ArrayList<>());
        }catch(Exception ex){

        }

        //获取总记录数

        int totalCount = ((int)criteria.setProjection((Projections.rowCount())).uniqueResult());
        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

        try {
            List innerOrderEntry = (List) field.get(criteria);
            for(int i=0;i<orderEntry.size();i++){
                innerOrderEntry.add(orderEntry.get(i));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List item = criteria.setFirstResult((currentNo-1)*pageSize).setMaxResults(pageSize).list();
        return new PageListImpl(item,new Long(totalCount).longValue(),pageSize,currentNo);
    }
}
