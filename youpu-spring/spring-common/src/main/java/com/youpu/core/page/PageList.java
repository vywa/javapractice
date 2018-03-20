package com.youpu.core.page;


import java.util.List;

/**
 * 分页查询返回的某页数据，主要是页数据和总记录数
 */
public interface PageList {

    /**
     * 返回示例数据标示
     * @return
     */
    public String getViewId();

    /**
     * 设置列数据标示
     * @param viewId
     */
    public void SetViewId(String viewId);

    /**
     * 返回某一页的数据
     * @return
     */
    public List getPageData();


    /**
     * 对页数据进行处理
     * @param list
     */
    public void setPageData(List list);

    /**
     * 返回总的记录数
     * @return
     */
    public long getTotalRows();


    /**
     * 返回每页记录数
     * @return
     */
    public int getPageSize();

    /**
     * 返回当前页号
     * @return
     */
    public int getCurrentPageNo();

    /**
     * 返回总页数
     * @return
     */
    public int getTotalPages();
}
