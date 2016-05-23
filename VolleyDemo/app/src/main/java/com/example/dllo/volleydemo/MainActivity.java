package com.example.dllo.volleydemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected  String url = "http://api.liwushuo.com/v2/banners?channel=IOS";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.iv_show);
        //使用Volley第一步:请求队列,所有的网络请求都加到改队列中

        RequestQueue queue = Volley.newRequestQueue(this);
        /**
         * 参数1 url
         * 参2 成功的listener
         * 参3 失败的listenter
         */
        //第二步创建请求
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);
                //数据请求成功
                //利用Gson解析数据
                Gson gson = new Gson();
                TestBean testBean = gson.fromJson(response,TestBean.class);

                Log.d("MainActivity", testBean.getData().getBanners().get(0).getChannel());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三歩 把请求加入到请求队列里
        queue.add(stringRequest);

        //加入请求头信息
        String urlHead = "http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=18625269142";
        StringRequest headRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response);
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> head = new HashMap<>();
                head.put("apikey","0bd6f1f851b3def6d92773006cc7c87a");
                return head;
            }
        };
        queue.add(headRequest);


        //解析jsonarray
        String urlArray = "http://chanyouji.com/api/trips/featured.json?page";
        StringRequest arrayRequest = new StringRequest(urlArray, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                //解析JsonArray
                List<ChanBean> chanBeanList;
                //type Token 的泛型就是Json数据了需要解析成的样子
                //解析JsonArray 就是需要填List<Bean>
                Type type = new TypeToken<List<ChanBean>>(){}.getType();
                chanBeanList = gson.fromJson(response,type);
                for (ChanBean b :
                        chanBeanList) {
                    Log.d("MainActivity", b.getName());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(arrayRequest);

        //请求图片
        String picUrl = "http://p.chanyouji.cn/323121/1445158400223p1a1t2ec5t7k0172j1pgd1m841nbf2.jpg";
        ImageLoader loader = new ImageLoader(queue, new MemoryCache());
//        loader.get(picUrl,ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        loader.get(picUrl,new ImageListenerWithAlpha(R.mipmap.ic_launcher,R.mipmap.ic_launcher,imageView));

    }
    class ImageListenerWithAlpha implements ImageLoader.ImageListener{
        int defaultImg,errorImg;
        ImageView imageView;

        public ImageListenerWithAlpha(int defaultImg, int errorImg, ImageView imageView) {
            this.defaultImg = defaultImg;
            this.errorImg = errorImg;
            this.imageView = imageView;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            if (response.getBitmap() != null) {
                imageView.setImageBitmap(response.getBitmap());
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"alpha",0,1);
                objectAnimator.setDuration(1000).start();
            } else if (defaultImg != 0) {
                imageView.setImageResource(defaultImg);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            if (errorImg != 0) {
                imageView.setImageResource(errorImg);
            }
        }
    }

}
