package com.youpu.core.base.dao;

import com.youpu.core.page.PageList;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 通用接口基类
 */
public interface BaseDaoHibernate {

    /**
     * 从数据库中删除某对象
     * @param object
     */
    public void delete(Object object);

    /**
     * 从数据库中删除指定的集合
     * @param collection
     */
    public void deleteAll(final Collection collection);


    /**
     * 根据对象的id从数据库中删除该对象
     * @param clazz
     * @param id
     */
    public void deleteById(Class clazz, Serializable id);


    /**
     * 获取所有对象
     * @param clazz po对象类型
     * @return po对象列表
     */
    public List getObjects(Class clazz);


    /**
     * 根据id获得po对象
     * @param clazz
     * @param id
     * @return
     */
    public Object getObject(Class clazz,Serializable id);


    /**
     * 保存po对象
     * @param object
     */
    public void saveObject(Object object);


    /**
     * 根据id删除po
     * @param clazz
     * @param id
     */
    public void removeObject(Class clazz,Serializable id);

    /**
     * 删除po对应的记录
     * @param object
     */
    public void removeObject(Object object);


    /**
     * 根据po建立一条记录
     * @param object
     * @return
     */
    public Object createObject(Object object);

    /**
     * 根据一条查询语句查询满足条件的记录
     * @param arg
     * @return
     */
    public List find(String arg);


    /**
     * 根据包括一个参数的查询语句，查询记录
     * @param arg
     * @param object
     * @return
     */
    public List find(String arg,Object object);


    /**
     * 根据多个查询参数查询
     * @param arg
     * @param objects
     * @return
     */
    public List find(String arg,Object[] objects);


    public PageList getPageListByCriteria(final DetachedCriteria detachedCriteria,final int currentNo, final int pageSize);
}
