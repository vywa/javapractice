package com.youpu;

import lombok.Data;

import java.util.Locale;

@Data
public class Article {

    public static final String DEFAULT_TEMPLATE = "frontViewArticle";

    private Locale.Category category;

    private String id;

    @ESearchTypeColumn
    private String title;
    private String link;
    private String color;
    private String image;
    private String keywords;

    @ESearchTypeColumn
    private String description;




}
