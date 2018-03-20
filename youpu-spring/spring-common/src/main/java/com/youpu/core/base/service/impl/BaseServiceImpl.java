package com.youpu.core.base.service.impl;

import com.youpu.core.base.dao.BaseDaoHibernate;
import com.youpu.core.base.service.BaseService;
import com.youpu.core.exception.TranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 服务层接口实现类
 */
public class BaseServiceImpl implements BaseService {

    protected final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Resource
    private BaseDaoHibernate baseDaoHibernate;

    @Override
    public void delete(Object o) throws TranException{
        try{
            baseDaoHibernate.delete(o);
        }catch (Exception ex){
            throw new TranException(ex.getMessage());
        }
    }

    @Override
    public void saveObject(Object o) throws TranException{
        try{
            baseDaoHibernate.saveObject(o);
        }catch (Exception ex){
            throw new TranException(ex.getMessage());
        }
    }
}
