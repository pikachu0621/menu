/**
 * 最要用于 F1 的数据整理与封装
 *
 */

package com.pikachu.menu.cls;



import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeF1Data {



    //轮播数据封装
    private List<Banner> banner;
    public static class Banner{


        private String url; // 跳转url
        private String imageUrl; // 图片url
        private String userImageUrl; // 作者图片url
        private String userNameStr; // 作者名
        private String titleStr; //标题
        private boolean isToDetails; //是否跳转到菜品详情页


        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUserImageUrl() {
            return userImageUrl;
        }

        public void setUserImageUrl(String userImageUrl) {
            this.userImageUrl = userImageUrl;
        }

        public String getUserNameStr() {
            return userNameStr;
        }

        public void setUserNameStr(String userNameStr) {
            this.userNameStr = userNameStr;
        }

        public String getTitleStr() {
            return titleStr;
        }

        public void setTitleStr(String titleStr) {
            this.titleStr = titleStr;
        }

        public boolean isToDetails() {
            return isToDetails;
        }

        public void setToDetails(boolean toDetails) {
            isToDetails = toDetails;
        }



    }


    //用于分类
    private List<Sort> sort;
    public static class Sort implements Serializable {


        public Sort(){}

        private String url; // 跳转url
        private String imageUrl; // 图片url
        private String titleStr; //标题
        private int toType; //要加载的样式  0 加载html  1加载分类   2加载json   3加载排行
        private boolean isSelected  = false; //是否选中

        public Sort(String url, String imageUrl, String titleStr, int toType, boolean isSelected) {
            this.url = url;
            this.imageUrl = imageUrl;
            this.titleStr = titleStr;
            this.toType = toType;
            this.isSelected = isSelected;
        }

        public Sort(String url, String imageUrl, String titleStr, int toType) {
            this.url = url;
            this.imageUrl = imageUrl;
            this.titleStr = titleStr;
            this.toType = toType;
        }


        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getToType() {
            return toType;
        }

        public void setToType(int toType) {
            this.toType = toType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Sort(String url) {
            this.url = url;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitleStr() {
            return titleStr;
        }

        public void setTitleStr(String titleStr) {
            this.titleStr = titleStr;
        }

        public static String toURl(String url){
            return AppInfo.APP_API_SORT + url;
        }


    }





    //用于html list
    public static class HtmlData{

        private String url; // 跳转url
        private String imageUrl; // 图片url
        private String userImageUrl; // 作者图片url
        private String userNameStr; // 作者名
        private String titleStr; //标题
        private boolean isToDetails; //是否跳转到菜品详情页


        //con_data_s2_list">


    }












    ////////////////////////////   轮播数据获取     ////////////////

    public static List<Banner> getBannerData(String htmlStr){

        String str1 = Tools.cutStr(htmlStr, "swiper-wrapper\">", "<script>");

        assert str1 != null;
        String[] split1 = str1.split("cj_swiper_item\">");

        List<Banner> banners = new ArrayList<>();
        for (String str2 : split1){


            //如果不包含则数据不符合
            if (str2 == null || str2.equals("") || !str2.contains("style=\"background"))
                continue;

            Banner banner = new Banner();

            // 跳转 url
            String url = Tools.cutStr(str2,"href=\"","\"");
            banner.setUrl(url);

            // 菜品大图url
            String imageUrl = Tools.cutStr(str2,"background: url(",")");
            banner.setImageUrl(imageUrl);

            //菜品名url
            String titleStr = Tools.cutStr(str2,"class=\"t\">","<");
            banner.setTitleStr(titleStr);


            //用户头像url
            String userImageUrl = Tools.cutStr(str2,"<img src=\"","\"");
            banner.setUserImageUrl(userImageUrl);

            //用户名
            String userNameStr = Tools.cutStr(str2,"authorname\">","<");
            banner.setUserNameStr(userNameStr);

            banners.add(banner);
        }

        return banners;
    }






    /////////////////////   获取分类数据     ////////////////////////////
    public static List<List<Sort>> getSortData(String htmlStr){

        List<Sort> sortRight = new ArrayList<>();
        List<Sort> sortsLift = new ArrayList<>();

        String str1 = Tools.cutStr(htmlStr, "left_sort_list_scroller\">", "</ul>");
        assert str1 != null;
        String[] split1 = str1.split("</a>");
        boolean isOne = true;
        for (String str2 : split1){

            //如果不包含则数据不符合
            if (str2 == null || str2.equals("") || !str2.contains("href=\"") ||
                    str2.contains("节日") || str2.contains("节气") || str2.contains("疾病调理") || str2.contains("功能性调理")
                    || str2.contains("工艺")|| str2.contains("时间")|| str2.contains("口味"))
                continue;

            Sort sort = new Sort();

            // 跳转 url
            String url = Tools.cutStr(str2,"href=\"","\"");
            sort.setUrl(url);

            //分类名url
            String titleStr = Tools.cutStr(str2,"<strong>","<");
            sort.setTitleStr(titleStr);

            sort.setToType(1);

            if (isOne){
                sort.setSelected(true);
                isOne = false;
            }else {
                sort.setSelected(false);
            }
            sortsLift.add(sort);
        }


        str1 = Tools.cutStr(htmlStr, "right_sort_list_scroller\">", "</ul>");
        assert str1 != null;
        split1 = str1.split("</a>");

        for (String str_b : split1){

            //如果不包含则数据不符合
            if (str_b == null || str_b.equals("") || !str_b.contains("href=\"") )
                continue;
            Sort sort = new Sort();

            // 跳转 url
            String url = Tools.cutStr(str_b,"href=\"","\"");
            sort.setUrl(url);

            // 图片url
            String imageUrl = Tools.cutStr(str_b,"src=\"","\"");
            sort.setImageUrl(imageUrl == null || imageUrl.equals("") ? null : imageUrl.contains("http") ? imageUrl : "http:"+imageUrl );

            //分类名url
            String titleStr = Tools.cutStr(str_b,"<span class","<");
            titleStr = Tools.cutStr(titleStr,">",null);
            sort.setTitleStr(titleStr);
            sort.setToType(0);
            sortRight.add(sort);
        }



        List<List<Sort>> lists = new ArrayList<>();
        lists.add(sortsLift);
        lists.add(sortRight);
        return lists;

    }








    /////////////////////    F1 header 点击事件数据    /////////////////
    public static List<Sort> getOnClickSort(){
        List<Sort> sorts = new ArrayList<>();
        sorts.add(new Sort(Sort.toURl("zaocan/"),null,"早餐",0));
        sorts.add(new Sort(Sort.toURl("wucan/"),null,"午餐",0));
        sorts.add(new Sort(Sort.toURl("wancan/"),null,"晚餐",0));
        sorts.add(new Sort(Sort.toURl("yexiao/"),null,"夜宵",0));
        sorts.add(new Sort(AppInfo.getUrl(3,1),null,"推荐肉食",2));
        sorts.add(new Sort(AppInfo.getUrl(2,1),null,"死令菜",2));
        sorts.add(new Sort(null,null,"菜谱分类",1));
        sorts.add(new Sort("https://m.meishij.net/html5/week.php",null,"人气排行",3));
        return sorts;
    }



}
