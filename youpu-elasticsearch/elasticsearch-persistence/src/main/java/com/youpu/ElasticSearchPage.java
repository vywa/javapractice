package com.youpu;

import lombok.Data;

import java.util.List;

@Data
public class ElasticSearchPage<T> {

    private String scrollId;
    private long total;
    private int pageSize;
    private int pageNum;
    private T param;
    private List<T> restList;
    private List<String> scrollIds;


}
