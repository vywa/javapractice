package com.youpu;

import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;

import javax.annotation.Resource;

public class ArticleRepositoryTest extends SearchBaseTest {

    @Resource
    TransportClientRepository client;

    @Test
    public void findByNameAndPrice() throws Exception{
        String result = client.getIndex("blog1","type","2006");
        System.out.println(result);
    }


    @Test
    public void saveDoc() throws Exception{
        Article article = new Article();
        article.setTitle("旗帜迎风飘扬");
        article.setDescription("东南太阳汽车出新车了DX9");
        article.setId("2006");
//        article.setHits(5);
//        article.setBeginDateString("2017/6/7");

        String result = client.saveDoc("blog1","type",article.getId(),article);

    }

    @Test
    public void searchFullText(){
        Article param = new Article();
        param.setDescription("太阳");
        ElasticSearchPage<Article> page = new ElasticSearchPage<Article>();
        page.setPageSize(10);
        HighlightBuilder builder = new HighlightBuilder();
        builder.field("description").preTags("<span style=\"color:red\">").postTags("</span>");
        page = client.searchFullText(param,page,builder,"blog1");

        for (Article aa : page.getRestList()){
            System.out.println(aa.getId()+"===="+aa.getDescription()+"=====title:====="+aa.getTitle());
        }

        System.out.println(page.getTotal());
    }


}
