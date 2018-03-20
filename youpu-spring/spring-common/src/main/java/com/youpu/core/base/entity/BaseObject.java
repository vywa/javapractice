package com.youpu.core.base.entity;

import java.io.Serializable;

public interface BaseObject extends Serializable{

    public String getOrderBy();
    public void setOrderBy(String orderBy);

    public int getPageSize();
    public void setPageSize(int pageSize);

    public int getCurrrentPageNo();
    public void setCurrentPaeNo(int currentPaeNo);

    public String getCondition();
    public void setCondition(String condition);

    public Object[] getConditionValues();
    public void setConditionValues(Object[] objects);




}
