package com.youpu.core.page.impl;

import com.youpu.core.page.PageList;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实现类
 */
@Data
public class PageListImpl implements PageList ,Serializable{

    private String viewId;
    private List pageDataList;
    private long totalRows;
    private int pageSize;
    private int totalPage;
    private int currentPageNo;

    public PageListImpl(List pageDataList, long totalRows) {
        this.pageDataList = pageDataList;
        this.totalRows = totalRows;
    }

    public PageListImpl(List pageDataList, long totalRows, int pageSize, int currentPageNo) {
        this.pageDataList = pageDataList;
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        this.currentPageNo = currentPageNo;
        this.setTotalPage(this.totalRows);
        int i = 1;
    }

    public void setTotalPage(long totalCount){
        if(totalCount>0){
            Double totalPage = (new Long(this.totalRows)).doubleValue()/this.pageSize;
            this.totalPage = totalPage.intValue();
        }else{
            this.totalPage = 0;
            this.currentPageNo = 0;
        }
    }

    @Override
    public String getViewId() {
        return null;
    }

    @Override
    public void SetViewId(String viewId) {

    }

    @Override
    public List getPageData() {
        return null;
    }

    @Override
    public void setPageData(List list) {

    }

    @Override
    public long getTotalRows() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public int getCurrentPageNo() {
        return 0;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }
}
