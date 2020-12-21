package com.pikachu.menu.cls;

import java.util.List;


public class JsonF1List {


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    /**
     * data : {"items":[{"type":"1","img":"https://st-cn.meishij.net/r/208/102/1025708/s1025708_157708987555505.jpg","title":"红薯粉皮","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"1025708","nickname":"美美家的厨房","avatar_url":"https://st-cn.meishij.net/user/208/102/ns1025708_143108176835021.jpg"},"path":"https://m.meishij.net/html5/zuofa/hongshufenpi_1.html","viewed_amount":"39","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/172/229/3994922/s3994922_157847452410478.jpg","title":"意大利肉酱鲜虾面","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"3994922","nickname":"Dream芸_","avatar_url":"https://st-cn.meishij.net/user/172/229/ns3994922_148015557430861.jpg"},"path":"https://m.meishij.net/html5/zuofa/yidaliroujiangxianxiamian.html","viewed_amount":"1k+","total_amount":0},{"type":"5","img":["https://st-cn.meishij.net/r/130/153/100880/a100880_144730889613448.jpg","https://st-cn.meishij.net/r/174/89/9334924/a9334924_148358884039048.jpg","https://st-cn.meishij.net/r/163/225/2431413/a2431413_146342991552877.jpg","https://st-cn.meishij.net/r/58/07/2939308/a2939308_147133184838462.jpg"],"title":"小寒的传统食物有哪些?","label":"","author":[],"path":"https://m.meishij.net/caipujihe/19156928.html","viewed_amount":0,"total_amount":"13"},{"type":"7","img":"https://st-cn.meishij.net/r/191/72/3955691/s3955691_157847815185283.jpg","title":"无糖酸奶","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"3955691","nickname":"grassdl","avatar_url":"https://st-cn.meishij.net/user/191/72/ns3955691_142961674569629.jpg"},"path":"https://m.meishij.net/html5/zuofa/wutangsuannai_2.html","viewed_amount":"2k+","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/92/193/9360842/s9360842_157847877941213.jpg","title":"苹果红枣甜汤","label":{"name":"汤羹","desc":"滋补养人又美味"},"author":{"id":"9360842","nickname":"行小丫","avatar_url":"https://st-cn.meishij.net/user/92/193/ns9360842_155749121775019.jpg"},"path":"https://m.meishij.net/html5/zuofa/pingguohongzaotiantang.html","viewed_amount":"2k+","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/97/178/13669597/s13669597_157848054528692.jpg","title":"猪油香辣海带丝","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"13669597","nickname":"归家小厨","avatar_url":"https://st-cn.meishij.net/user/97/178/ns13669597_155393043063429.jpg"},"path":"https://m.meishij.net/html5/zuofa/zhuyouxianglahaidaisi.html","viewed_amount":"1k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/47/07/14189297/s14189297_157848348267046.jpg","title":"玉米炒肉沫","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"14189297","nickname":"为食猫_XYZ","avatar_url":"https://st-cn.meishij.net/user/47/07/ns14189297_157354351142873.jpg"},"path":"https://m.meishij.net/html5/zuofa/yumichaoroumo_4.html","viewed_amount":"3k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/30/75/4706280/s4706280_157847234118827.jpg","title":"冬瓜饺子","label":{"name":"主食","desc":"样式精致味道好"},"author":{"id":"4706280","nickname":"雪峰儿","avatar_url":"https://st-cn.meishij.net/user/30/75/ns4706280_157196159259154.jpg"},"path":"https://m.meishij.net/html5/zuofa/dongguajiaozi_1.html","viewed_amount":"476","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/30/75/4706280/s4706280_157847137268568.jpg","title":"原味KT饼干","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"4706280","nickname":"雪峰儿","avatar_url":"https://st-cn.meishij.net/user/30/75/ns4706280_157196159259154.jpg"},"path":"https://m.meishij.net/html5/zuofa/yuanweiktbinggan.html","viewed_amount":"2k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/238/55/6763988/s6763988_157847122370715.jpg","title":"西兰花面条鱼","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"6763988","nickname":"七九星星","avatar_url":"https://st-cn.meishij.net/user/238/55/ns6763988_147083265829167.jpg"},"path":"https://m.meishij.net/html5/zuofa/xilanhuamiantiaoyu.html","viewed_amount":"518","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/147/116/13279147/s13279147_157848228539586.jpg","title":"白灼芦笋","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"13279147","nickname":"HK大廚","avatar_url":"https://st-cn.meishij.net/user/147/116/ns13279147_154450896962900.jpg"},"path":"https://m.meishij.net/html5/zuofa/baizhuolusun_18.html","viewed_amount":"2k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/165/105/7651415/s7651415_157848156316661.jpg","title":"蒜蓉粉丝蒸虾","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"7651415","nickname":"橙子小厨","avatar_url":"https://st-cn.meishij.net/user/165/105/ns7651415_147168121259643.jpg"},"path":"https://m.meishij.net/html5/zuofa/suanrongfensizhengxia_41.html","viewed_amount":"1k+","total_amount":0}],"total_page":1000,"current_page":"3"}
     */

    private DataBean data;

    public static class DataBean {
        public Integer getTotal_page() {
            return total_page;
        }

        public void setTotal_page(Integer total_page) {
            this.total_page = total_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        /**
         * items : [{"type":"1","img":"https://st-cn.meishij.net/r/208/102/1025708/s1025708_157708987555505.jpg","title":"红薯粉皮","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"1025708","nickname":"美美家的厨房","avatar_url":"https://st-cn.meishij.net/user/208/102/ns1025708_143108176835021.jpg"},"path":"https://m.meishij.net/html5/zuofa/hongshufenpi_1.html","viewed_amount":"39","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/172/229/3994922/s3994922_157847452410478.jpg","title":"意大利肉酱鲜虾面","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"3994922","nickname":"Dream芸_","avatar_url":"https://st-cn.meishij.net/user/172/229/ns3994922_148015557430861.jpg"},"path":"https://m.meishij.net/html5/zuofa/yidaliroujiangxianxiamian.html","viewed_amount":"1k+","total_amount":0},{"type":"5","img":["https://st-cn.meishij.net/r/130/153/100880/a100880_144730889613448.jpg","https://st-cn.meishij.net/r/174/89/9334924/a9334924_148358884039048.jpg","https://st-cn.meishij.net/r/163/225/2431413/a2431413_146342991552877.jpg","https://st-cn.meishij.net/r/58/07/2939308/a2939308_147133184838462.jpg"],"title":"小寒的传统食物有哪些?","label":"","author":[],"path":"https://m.meishij.net/caipujihe/19156928.html","viewed_amount":0,"total_amount":"13"},{"type":"7","img":"https://st-cn.meishij.net/r/191/72/3955691/s3955691_157847815185283.jpg","title":"无糖酸奶","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"3955691","nickname":"grassdl","avatar_url":"https://st-cn.meishij.net/user/191/72/ns3955691_142961674569629.jpg"},"path":"https://m.meishij.net/html5/zuofa/wutangsuannai_2.html","viewed_amount":"2k+","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/92/193/9360842/s9360842_157847877941213.jpg","title":"苹果红枣甜汤","label":{"name":"汤羹","desc":"滋补养人又美味"},"author":{"id":"9360842","nickname":"行小丫","avatar_url":"https://st-cn.meishij.net/user/92/193/ns9360842_155749121775019.jpg"},"path":"https://m.meishij.net/html5/zuofa/pingguohongzaotiantang.html","viewed_amount":"2k+","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/97/178/13669597/s13669597_157848054528692.jpg","title":"猪油香辣海带丝","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"13669597","nickname":"归家小厨","avatar_url":"https://st-cn.meishij.net/user/97/178/ns13669597_155393043063429.jpg"},"path":"https://m.meishij.net/html5/zuofa/zhuyouxianglahaidaisi.html","viewed_amount":"1k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/47/07/14189297/s14189297_157848348267046.jpg","title":"玉米炒肉沫","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"14189297","nickname":"为食猫_XYZ","avatar_url":"https://st-cn.meishij.net/user/47/07/ns14189297_157354351142873.jpg"},"path":"https://m.meishij.net/html5/zuofa/yumichaoroumo_4.html","viewed_amount":"3k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/30/75/4706280/s4706280_157847234118827.jpg","title":"冬瓜饺子","label":{"name":"主食","desc":"样式精致味道好"},"author":{"id":"4706280","nickname":"雪峰儿","avatar_url":"https://st-cn.meishij.net/user/30/75/ns4706280_157196159259154.jpg"},"path":"https://m.meishij.net/html5/zuofa/dongguajiaozi_1.html","viewed_amount":"476","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/30/75/4706280/s4706280_157847137268568.jpg","title":"原味KT饼干","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"4706280","nickname":"雪峰儿","avatar_url":"https://st-cn.meishij.net/user/30/75/ns4706280_157196159259154.jpg"},"path":"https://m.meishij.net/html5/zuofa/yuanweiktbinggan.html","viewed_amount":"2k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/238/55/6763988/s6763988_157847122370715.jpg","title":"西兰花面条鱼","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"6763988","nickname":"七九星星","avatar_url":"https://st-cn.meishij.net/user/238/55/ns6763988_147083265829167.jpg"},"path":"https://m.meishij.net/html5/zuofa/xilanhuamiantiaoyu.html","viewed_amount":"518","total_amount":0},{"type":"7","img":"https://st-cn.meishij.net/r/147/116/13279147/s13279147_157848228539586.jpg","title":"白灼芦笋","label":{"name":"热门","desc":"最受欢迎的美食"},"author":{"id":"13279147","nickname":"HK大廚","avatar_url":"https://st-cn.meishij.net/user/147/116/ns13279147_154450896962900.jpg"},"path":"https://m.meishij.net/html5/zuofa/baizhuolusun_18.html","viewed_amount":"2k+","total_amount":0},{"type":"1","img":"https://st-cn.meishij.net/r/165/105/7651415/s7651415_157848156316661.jpg","title":"蒜蓉粉丝蒸虾","label":{"name":"家常菜","desc":"天涯海角最念的味道"},"author":{"id":"7651415","nickname":"橙子小厨","avatar_url":"https://st-cn.meishij.net/user/165/105/ns7651415_147168121259643.jpg"},"path":"https://m.meishij.net/html5/zuofa/suanrongfensizhengxia_41.html","viewed_amount":"1k+","total_amount":0}]
         * total_page : 1000
         * current_page : 3
         */

        private Integer total_page;
        private String current_page;
        private List<ItemsBean> items;


        public static class ItemsBean {
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public LabelBean getLabel() {
                return label;
            }

            public void setLabel(LabelBean label) {
                this.label = label;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getViewed_amount() {
                return viewed_amount;
            }

            public void setViewed_amount(String viewed_amount) {
                this.viewed_amount = viewed_amount;
            }

            public Integer getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(Integer total_amount) {
                this.total_amount = total_amount;
            }

            /**
             * type : 1
             * img : https://st-cn.meishij.net/r/208/102/1025708/s1025708_157708987555505.jpg
             * title : 红薯粉皮
             * label : {"name":"家常菜","desc":"天涯海角最念的味道"}
             * author : {"id":"1025708","nickname":"美美家的厨房","avatar_url":"https://st-cn.meishij.net/user/208/102/ns1025708_143108176835021.jpg"}
             * path : https://m.meishij.net/html5/zuofa/hongshufenpi_1.html
             * viewed_amount : 39
             * total_amount : 0
             */

            private String type;
            private String img;
            private String title;
            private LabelBean label;
            private AuthorBean author;
            private String path;
            private String viewed_amount;
            private Integer total_amount;


            public static class LabelBean {
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                /**
                 * name : 家常菜
                 * desc : 天涯海角最念的味道
                 */

                private String name;
                private String desc;
            }


            public static class AuthorBean {
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                /**
                 * id : 1025708
                 * nickname : 美美家的厨房
                 * avatar_url : https://st-cn.meishij.net/user/208/102/ns1025708_143108176835021.jpg
                 */

                private String id;
                private String nickname;
                private String avatar_url;
            }
        }
    }
}
