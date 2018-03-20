package com.youpu.core.base.service;


import com.youpu.core.exception.TranException;

/**
 * 服务层基础接口类
 */
public interface BaseService {

    public void delete(Object o) throws TranException;

    public void saveObject(Object o) throws TranException;
}
