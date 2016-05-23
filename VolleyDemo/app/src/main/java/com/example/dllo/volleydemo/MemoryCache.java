package com.example.dllo.volleydemo;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/5/23
 * 图片的内存缓存.
 */

public class MemoryCache implements ImageLoader.ImageCache{

    private LruCache<String,Bitmap> lruCache;

    public MemoryCache() {
        //需要告诉LruCache 的最大占用内存数
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/8/1024);
       lruCache = new LruCache<String, Bitmap>(maxSize){
           //返回每一个Value他所占用的内存数
           @Override
           protected int sizeOf(String key, Bitmap value) {

               return value.getRowBytes() * value.getHeight()/1024;
           }
       };

    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
