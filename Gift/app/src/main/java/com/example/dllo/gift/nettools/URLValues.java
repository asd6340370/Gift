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
    //攻略详情页
    public static final String DETAILS_RAIDER_BEFORE = "http://api.liwushuo.com/v2/posts/";
    public static final String DETAILS_RAIDER_AFTER = "?show_baichuan=1";
    //分类
    public static final String CATEGORY_GIFT = "http://api.liwushuo.com/v2/item_categories/tree";
    //礼物列表圆形图片跳转
    public static final String CATEGORY_GIFT_DETAILS_BEFORE = "http://api.liwushuo.com/v2/item_subcategories/";
    public static final String CATEGORY_GIFT_DETAILS_AFTER =  "/items?limit=20&offset=0";


    //攻略专题图片
    public static final String CATEGORY_RAIDER_HEADER_BANNER = "http://api.liwushuo.com/v2/collections?limit=10&offset=0";
//    点击图片跳转页面
    public static final String  DETAILS_SPECIAL_BEFORE = "http://api.liwushuo.com/v2/collections/";
    public static final String  DETAILS_SPECIAL_AFTER = "/posts?limit=20&offset=0";
    //攻略列表
    public static final String CATEGORY_RAIDER = "http://api.liwushuo.com/v2/channel_groups/all";
    //攻略列表圆形图片跳转
    public static final String CATEGORY_RAIDER_DETAILS_BEFORE = "http://api.liwushuo.com/v2/channels/";
    public static final String CATEGORY_RAIDER_DETAILS_AFTER =  "/items?limit=20&offset=0";


}
