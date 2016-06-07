package com.example.dllo.gift.details.detailsbean;

import java.util.List;

/**
 * Created by dllo on 16/6/7.
 */
public class CategoryGiftSelectionMenuBean {

    /**
     * code : 200
     * data : {"filters":[{"channels":[{"group_id":1,"icon_url":null,"id":16,"items_count":0,"key":16,"name":"男票","order":7,"status":0},{"group_id":1,"icon_url":null,"id":17,"items_count":0,"key":17,"name":"女盆友","order":6,"status":0},{"group_id":1,"icon_url":null,"id":18,"items_count":0,"key":18,"name":"闺蜜们","order":5,"status":0},{"group_id":1,"icon_url":null,"id":19,"items_count":0,"key":19,"name":"基友","order":4,"status":0},{"group_id":1,"icon_url":null,"id":20,"items_count":0,"key":20,"name":"爸爸妈妈","order":3,"status":0},{"group_id":1,"icon_url":null,"id":21,"items_count":0,"key":21,"name":"小盆友","order":2,"status":0},{"group_id":1,"icon_url":null,"id":22,"items_count":0,"key":22,"name":"同事","order":1,"status":0}],"id":1,"key":"target","name":"对象","order":4,"status":0},{"channels":[{"group_id":3,"icon_url":null,"id":30,"items_count":0,"key":30,"name":"生日","order":9,"status":0},{"group_id":3,"icon_url":null,"id":31,"items_count":0,"key":31,"name":"情人节","order":8,"status":0},{"group_id":3,"icon_url":null,"id":32,"items_count":0,"key":32,"name":"结婚","order":7,"status":0},{"group_id":3,"icon_url":null,"id":33,"items_count":0,"key":33,"name":"新年","order":6,"status":0},{"group_id":3,"icon_url":null,"id":34,"items_count":0,"key":34,"name":"感谢","order":5,"status":0},{"group_id":3,"icon_url":null,"id":35,"items_count":0,"key":35,"name":"纪念日","order":4,"status":0},{"group_id":3,"icon_url":null,"id":36,"items_count":0,"key":36,"name":"乔迁","order":3,"status":0},{"group_id":3,"icon_url":null,"id":37,"items_count":0,"key":37,"name":"圣诞节","order":2,"status":0}],"id":3,"key":"scene","name":"场合","order":3,"status":0},{"channels":[{"group_id":2,"icon_url":null,"id":26,"items_count":0,"key":26,"name":"萌","order":6,"status":0},{"group_id":2,"icon_url":null,"id":28,"items_count":0,"key":28,"name":"小清新","order":4,"status":0},{"group_id":2,"icon_url":null,"id":43,"items_count":0,"key":43,"name":"创意","order":0,"status":0},{"group_id":2,"icon_url":null,"id":44,"items_count":0,"key":44,"name":"奇葩","order":0,"status":0},{"group_id":2,"icon_url":null,"id":45,"items_count":0,"key":45,"name":"文艺范","order":0,"status":0},{"group_id":2,"icon_url":null,"id":46,"items_count":0,"key":46,"name":"科技感","order":0,"status":0},{"group_id":2,"icon_url":null,"id":47,"items_count":0,"key":47,"name":"设计感","order":0,"status":0}],"id":2,"key":"personality","name":"个性","order":2,"status":0},{"channels":[{"group_id":4,"icon_url":null,"id":38,"items_count":0,"key":"0_50","name":"50以下","order":6,"status":0},{"group_id":4,"icon_url":null,"id":39,"items_count":0,"key":"50_200","name":"50-200","order":5,"status":0},{"group_id":4,"icon_url":null,"id":40,"items_count":0,"key":"200_500","name":"200-500","order":4,"status":0},{"group_id":4,"icon_url":null,"id":41,"items_count":0,"key":"500_1000","name":"500-1000","order":3,"status":0},{"group_id":4,"icon_url":null,"id":42,"items_count":0,"key":"1000_100000","name":"1000以上","order":2,"status":0}],"id":4,"key":"price","name":"价格","order":1,"status":0}]}
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
         * channels : [{"group_id":1,"icon_url":null,"id":16,"items_count":0,"key":16,"name":"男票","order":7,"status":0},{"group_id":1,"icon_url":null,"id":17,"items_count":0,"key":17,"name":"女盆友","order":6,"status":0},{"group_id":1,"icon_url":null,"id":18,"items_count":0,"key":18,"name":"闺蜜们","order":5,"status":0},{"group_id":1,"icon_url":null,"id":19,"items_count":0,"key":19,"name":"基友","order":4,"status":0},{"group_id":1,"icon_url":null,"id":20,"items_count":0,"key":20,"name":"爸爸妈妈","order":3,"status":0},{"group_id":1,"icon_url":null,"id":21,"items_count":0,"key":21,"name":"小盆友","order":2,"status":0},{"group_id":1,"icon_url":null,"id":22,"items_count":0,"key":22,"name":"同事","order":1,"status":0}]
         * id : 1
         * key : target
         * name : 对象
         * order : 4
         * status : 0
         */

        private List<FiltersBean> filters;

        public List<FiltersBean> getFilters() {
            return filters;
        }

        public void setFilters(List<FiltersBean> filters) {
            this.filters = filters;
        }

        public static class FiltersBean {
            private int id;
            private String key;
            private String name;
            private int order;
            private int status;
            /**
             * group_id : 1
             * icon_url : null
             * id : 16
             * items_count : 0
             * key : 16
             * name : 男票
             * order : 7
             * status : 0
             */

            private List<ChannelsBean> channels;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public List<ChannelsBean> getChannels() {
                return channels;
            }

            public void setChannels(List<ChannelsBean> channels) {
                this.channels = channels;
            }

            public static class ChannelsBean {
                private int group_id;
                private Object icon_url;
                private int id;
                private int items_count;
                private String key;
                private String name;
                private int order;
                private int status;

                public int getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

                public Object getIcon_url() {
                    return icon_url;
                }

                public void setIcon_url(Object icon_url) {
                    this.icon_url = icon_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getItems_count() {
                    return items_count;
                }

                public void setItems_count(int items_count) {
                    this.items_count = items_count;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
            }
        }
    }
}
