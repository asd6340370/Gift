package com.example.dllo.gift.nettools;

/**
 * Created by dllo on 16/5/24.
 */
public final class URLValues {

    public static final String HOT_URL = "http://api.liwushuo.com/v2/items?limit=20&offset=0&gender=2&generation=1";
    public static final String HOT_URL_NEW = "http://api.liwushuo.com/v2/items?generation=1&gender=2&limit=20&offset=40";

    public static final String DISCOVER_SPECIAL_BANNER = "http://api.liwushuo.com/v2/banners";
    public static final String DISCOVER_SPECIAL_LIST_Header = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    public static final String DISCOVER_SPECIAL_LIST = "http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1";
    public static final String DISCOVER_TABLAYOUT_TITLES = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
    //评论
    public static final String COMMENTS_BEFORE = "http://api.liwushuo.com/v2/posts/";
    public static final String COMMENTS_HOT_AFTER = "/hot_comments?limit=20&offset=0";
    public static final String COMMENTS_AFTER = "/comments?limit=20&offset=0";
    public static final String COMMENTS_HOT_TOP = "&dataset=top";
    public static final String COMMENTS_HOT_OTHER = "&dataset=all";
    //分类
    public static final String CATEGORY_GIFT = "http://api.liwushuo.com/v2/item_categories/tree";




}
