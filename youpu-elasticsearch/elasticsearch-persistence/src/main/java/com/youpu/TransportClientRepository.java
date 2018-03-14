package com.youpu;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.search.Query;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.HppcMaps;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransportClientRepository {
    private TransportClient client;

    public TransportClientRepository(TransportClient client) {
        super();
        this.client = client;
    }

    /**
     *  创建搜索引擎文档
     * @param index 索引名称
     * @param type 索引类型
     * @param id 索引id
     * @param doc
     * @return
     */
    public String saveDoc(String index,String type,String id,Object doc){
        IndexResponse response = client.prepareIndex(index,type,id).setSource(getXContentBuilderKeyValue(doc)).get();
        return response.getId();
    }

    /**
     * 更新文档
     * @param index
     * @param type
     * @param id
     * @param doc
     * @return
     */
    public String updateDoc(String index,String type,String id,Object doc){
        UpdateResponse response = client.prepareUpdate(index,type,id).setDoc(getXContentBuilderKeyValue(doc)).get();
        return response.getId();
    }


    /**
     * 删除索引
     * @param index
     * @param type
     * @param id
     * @return
     */
    public String deleteById(String index,String type,String id){
        DeleteResponse response = client.prepareDelete(index,type,id).get();
        return response.getId();
    }

    /**
     * 获取索引对应的存储内容
     * @param index
     * @param type
     * @param id
     * @return
     */
    public String getIndex(String index,String type,String id){
        GetResponse response = client.prepareGet(index,type,id).get();
        if(response.isExists()){
            return response.getSourceAsString();
        }
        return null;
    }

    /**
     * 对象转换
     * @param t
     * @param src
     * @param <T>
     * @return
     */
    private <T> T parseObject(T t,String src){
        try{
            return (T)JSON.parseObject(src,t.getClass());
        }catch(Exception ex){

        }
        return null;
    }

    /**
     * 获取索引对应的存储内容，转换成对象的方式
     * @param index
     * @param type
     * @param id
     * @param t
     * @param <T>
     * @return
     */
    public <T> T getIdx(String index,String type,String id,T t){
        return parseObject(t,getIndex(index,type,id));
    }


    public void searchFullText(String field,String queryValue,int pageNum,int pageSize,String... indexs){
        QueryBuilder builder = QueryBuilders.matchQuery(field,queryValue);
        SearchResponse scrollRespose = client.prepareSearch(indexs).addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
                .setFrom(pageNum*pageSize).setSize(pageSize).setScroll(new TimeValue(60000)).setQuery(builder).get();

        do {
            for(SearchHit hit:scrollRespose.getHits().getHits()){
                System.out.println("result:"+hit.getSourceAsString());
            }
            scrollRespose = client.prepareSearchScroll(scrollRespose.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();

        } while (scrollRespose.getHits().getHits().length!=0);

    }

    public <T> ElasticSearchPage<T> searchFullText(T param,ElasticSearchPage<T> page,String... indexs){
        QueryBuilder builder = null;
        Map<String,Object> map = getObjectMap(param);
        if(map==null){
            return null;
        }

        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=null){
                builder = QueryBuilders.matchQuery(entry.getKey(),entry.getValue());
            }
        }

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title").field("description");
        SearchResponse response = client.prepareSearch(indexs).setFrom(page.getPageNum()*page.getPageSize()).highlighter(highlightBuilder)
                .setSize(page.getPageSize()).setQuery(builder).get();

        List<T> result = new ArrayList<T>();
        for(SearchHit hit :response.getHits().getHits()){
            try{
                Map<String,HighlightField> highlightResult = hit.getHighlightFields();
                highlightResult.get("description");
                result.add(parseObject(param,hit.getSourceAsString()));
            }catch (Exception ex){

            }


        }
        return null;

    }

    private <T> Map<String,Object> getObjectMap(T param) {
        return null;
    }

    /**
     * 判断某个索引下type是否存在
     * @param index
     * @param type
     * @return
     */
    public boolean isTypeExist(String index,String type){
        return client.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet().isExists();
    }

    /**
     * 创建type,存在则进行更新
     * @param index 索引名称
     * @param type type名称
     * @param object 要设置type的object
     * @return
     */
    public boolean createType(String index,String type,Object object){
        if(!isIndexExsit(index)){
            return false;
        }

        try{
            return client.admin().indices().preparePutMapping(index).setType(type).setSource(object).get().isAcknowledged();

        } catch (Exception ex){
            return false;

        }
    }

    /**
     * 判断索引是否存在
     * @param index
     * @return
     */
    private boolean isIndexExsit(String index) {
        return client.admin().indices().prepareExists(index).execute().actionGet().isExists();
    }

    private byte[] getXContentBuilderKeyValue(Object doc) {
        return null;
    }
}
