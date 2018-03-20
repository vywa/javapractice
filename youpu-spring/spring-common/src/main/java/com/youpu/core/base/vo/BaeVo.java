package com.youpu.core.base.vo;

import lombok.Data;

import java.util.List;

@Data
public class BaeVo {

    private int currentPageNo=1;
    private int pageSize = 15;
    private String orderBy;
    private String condition;

    private Object[] valueConditons;

    private List resultList;

}
