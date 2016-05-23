package com.example.dllo.volleydemo;


import java.util.List;

/**
 * Created by dllo on 16/5/23.
 */
public class TestBean {

    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":598,"image_url":"http://img02.liwushuo.com/image/160520/7rgiyl2c9.jpg-w720","order":72,"status":0,"target_id":null,"target_type":"url","target_url":"liwushuo:///page?url=http%3A%2F%2Fredirect.liwushuo.com%2Fj%2Fjiyoujia&page_action=navigation&login=false&type=url","type":"url","webp_url":"http://img02.liwushuo.com/image/160520/7rgiyl2c9.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":586,"image_url":"http://img01.liwushuo.com/image/160513/8nfx4ck0f.jpg-w720","order":67,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/160513/p5gokuphi.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/160513/p5gokuphi.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/160513/futszgbam.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/160513/futszgbam.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1463140513,"id":280,"posts_count":7,"status":1,"subtitle":"品质耳机合集","title":"戴上耳机，在音乐里世界倾听美好","updated_at":1463140513},"target_id":280,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=280","type":"collection","webp_url":"http://img01.liwushuo.com/image/160513/8nfx4ck0f.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":591,"image_url":"http://img01.liwushuo.com/image/160517/9emzlc36x.jpg-w720","order":66,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/160518/v8su6youf.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/160518/v8su6youf.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/160518/7doo6pm3c.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/160518/7doo6pm3c.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1463452367,"id":286,"posts_count":7,"status":0,"subtitle":"让你的时尚指数UP↑UP↑","title":"一周穿搭指南","updated_at":1463452367},"target_id":286,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=286","type":"collection","webp_url":"http://img01.liwushuo.com/image/160517/9emzlc36x.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":588,"image_url":"http://img02.liwushuo.com/image/160513/zt18q0hth.jpg-w720","order":65,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/160513/4m3a7gr3h.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/160513/4m3a7gr3h.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/160513/q8sq2pv5y.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/160513/q8sq2pv5y.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1463140550,"id":281,"posts_count":8,"status":1,"subtitle":"防晒有道","title":"不想被\u201c黑\u201d，做好防晒才是正经事","updated_at":1463140550},"target_id":281,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=281","type":"collection","webp_url":"http://img02.liwushuo.com/image/160513/zt18q0hth.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":592,"image_url":"http://img01.liwushuo.com/image/160517/b341vuy7v.jpg-w720","order":0,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/160517/reej52gyl.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/160517/reej52gyl.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160517/gv52omhon.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160517/gv52omhon.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1463452480,"id":287,"posts_count":8,"status":1,"subtitle":"怎么背都是少女范儿","title":"最爱双肩包","updated_at":1463452480},"target_id":287,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=287","type":"collection","webp_url":"http://img01.liwushuo.com/image/160517/b341vuy7v.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * channel : all
         * id : 598
         * image_url : http://img02.liwushuo.com/image/160520/7rgiyl2c9.jpg-w720
         * order : 72
         * status : 0
         * target_id : null
         * target_type : url
         * target_url : liwushuo:///page?url=http%3A%2F%2Fredirect.liwushuo.com%2Fj%2Fjiyoujia&page_action=navigation&login=false&type=url
         * type : url
         * webp_url : http://img02.liwushuo.com/image/160520/7rgiyl2c9.jpg?imageView2/2/w/720/q/85/format/webp
         */

        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            private Object target_id;
            private String target_type;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getTarget_id() {
                return target_id;
            }

            public void setTarget_id(Object target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
