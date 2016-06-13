package com.example.dllo.gift.comments;

import java.util.List;

/**
 * Created by dllo on 16/5/31.
 */
public class CommentsBean {

    /**
     * code : 200
     * data : {"comments":[{"content":"一开始觉得素颜是最美我素颜我骄傲，于是从来没化过妆弄过头发，后来真的感觉人要活得精彩一点就要适当的把自己打扮得美丽一点，因为现在就是个看脸的社会啊。重点来了，我素颜一样美哈哈哈","created_at":1464192380,"does_like":false,"fake_likes_count":0,"id":1084326,"likes_count":20,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20160526/5oaami465_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":2536917,"nickname":"Butterfly","role":0}},{"content":"其实我想说素颜最美的，但现在不化妆真的不好意思出门啊。","created_at":1464183406,"does_like":false,"fake_likes_count":0,"id":1082377,"likes_count":17,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/151102/15d319fa2_a-w180","can_mobile_login":true,"guest_uuid":null,"id":5124471,"nickname":"末夜","role":0}},{"content":"会化妆的拿啥都能画，像我这样的手残党，拿一堆神器画出来也是鬼😂","created_at":1464169344,"does_like":false,"fake_likes_count":0,"id":1080754,"likes_count":7,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150730/c52eb86a1_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2960133,"nickname":"浅笑心柔◎","role":0}},{"content":"其实吧。。颜才是决定这些东西有没有卵用的关键。。。。","created_at":1464171454,"does_like":false,"fake_likes_count":0,"id":1080866,"likes_count":3,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150131/b4d98e894_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":1204328,"nickname":"录音匣子(｡･ω･｡)","role":0}},{"content":"什么好用不好用的，遇到手残一切完蛋","created_at":1464172560,"does_like":false,"fake_likes_count":0,"id":1080935,"likes_count":2,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/20160215/5owc1kljt_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":6618203,"nickname":"effaceable","role":0}}],"paging":{"next_url":"http://api.liwushuo.com/v2/posts/1024345/hot_comments?dataset=top&limit=5&offset=5"}}
     * message : OK
     */

    private int code;
    /**
     * comments : [{"content":"一开始觉得素颜是最美我素颜我骄傲，于是从来没化过妆弄过头发，后来真的感觉人要活得精彩一点就要适当的把自己打扮得美丽一点，因为现在就是个看脸的社会啊。重点来了，我素颜一样美哈哈哈","created_at":1464192380,"does_like":false,"fake_likes_count":0,"id":1084326,"likes_count":20,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20160526/5oaami465_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":2536917,"nickname":"Butterfly","role":0}},{"content":"其实我想说素颜最美的，但现在不化妆真的不好意思出门啊。","created_at":1464183406,"does_like":false,"fake_likes_count":0,"id":1082377,"likes_count":17,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/151102/15d319fa2_a-w180","can_mobile_login":true,"guest_uuid":null,"id":5124471,"nickname":"末夜","role":0}},{"content":"会化妆的拿啥都能画，像我这样的手残党，拿一堆神器画出来也是鬼😂","created_at":1464169344,"does_like":false,"fake_likes_count":0,"id":1080754,"likes_count":7,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150730/c52eb86a1_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2960133,"nickname":"浅笑心柔◎","role":0}},{"content":"其实吧。。颜才是决定这些东西有没有卵用的关键。。。。","created_at":1464171454,"does_like":false,"fake_likes_count":0,"id":1080866,"likes_count":3,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150131/b4d98e894_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":1204328,"nickname":"录音匣子(｡･ω･｡)","role":0}},{"content":"什么好用不好用的，遇到手残一切完蛋","created_at":1464172560,"does_like":false,"fake_likes_count":0,"id":1080935,"likes_count":2,"post_id":1024345,"reply_to_id":null,"user":{"avatar_url":"http://img03.liwushuo.com/avatar/20160215/5owc1kljt_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":6618203,"nickname":"effaceable","role":0}}]
     * paging : {"next_url":"http://api.liwushuo.com/v2/posts/1024345/hot_comments?dataset=top&limit=5&offset=5"}
     */

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
         * next_url : http://api.liwushuo.com/v2/posts/1024345/hot_comments?dataset=top&limit=5&offset=5
         */

        private PagingBean paging;
        /**
         * content : 一开始觉得素颜是最美我素颜我骄傲，于是从来没化过妆弄过头发，后来真的感觉人要活得精彩一点就要适当的把自己打扮得美丽一点，因为现在就是个看脸的社会啊。重点来了，我素颜一样美哈哈哈
         * created_at : 1464192380
         * does_like : false
         * fake_likes_count : 0
         * id : 1084326
         * likes_count : 20
         * post_id : 1024345
         * reply_to_id : null
         * user : {"avatar_url":"http://img02.liwushuo.com/avatar/20160526/5oaami465_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":2536917,"nickname":"Butterfly","role":0}
         */

        private List<CommentBean> comments;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CommentBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentBean> comments) {
            this.comments = comments;
        }

        public static class PagingBean {
            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class CommentBean {
            private String content;
            private int created_at;
            private boolean does_like;
            private int fake_likes_count;
            private int id;
            private int likes_count;
            private int post_id;
            private Object reply_to_id;
            /**
             * avatar_url : http://img02.liwushuo.com/avatar/20160526/5oaami465_i.png-w180
             * can_mobile_login : true
             * guest_uuid : null
             * id : 2536917
             * nickname : Butterfly
             * role : 0
             */

            private UserBean user;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public boolean isDoes_like() {
                return does_like;
            }

            public void setDoes_like(boolean does_like) {
                this.does_like = does_like;
            }

            public int getFake_likes_count() {
                return fake_likes_count;
            }

            public void setFake_likes_count(int fake_likes_count) {
                this.fake_likes_count = fake_likes_count;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLikes_count() {
                return likes_count;
            }

            public void setLikes_count(int likes_count) {
                this.likes_count = likes_count;
            }

            public int getPost_id() {
                return post_id;
            }

            public void setPost_id(int post_id) {
                this.post_id = post_id;
            }

            public Object getReply_to_id() {
                return reply_to_id;
            }

            public void setReply_to_id(Object reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }
    }
}
