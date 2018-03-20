package com.youpu.core.base.entity;

import lombok.Data;

@Data
public class AbstractBaseObject implements BaseObject {

    protected String id;
    protected String orderBy;
    protected int pageSize;

    protected int currentPageNo;
    protected String condition;
    protected Object[] conditionValues;

    @Override
    public String getOrderBy() {
        return null;
    }

    @Override
    public void setOrderBy(String orderBy) {

    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public void setPageSize(int pageSize) {

    }

    @Override
    public int getCurrrentPageNo() {
        return 0;
    }

    @Override
    public void setCurrentPaeNo(int currentPaeNo) {

    }

    @Override
    public String getCondition() {
        return null;
    }

    @Override
    public void setCondition(String condition) {

    }

    @Override
    public Object[] getConditionValues() {
        return new Object[0];
    }

    @Override
    public void setConditionValues(Object[] objects) {

    }
}
