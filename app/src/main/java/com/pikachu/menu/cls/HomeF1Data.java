/**
 * 最要用于 F1 的数据整理与封装
 *
 */

package com.pikachu.menu.cls;



import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;

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
    public static class Sort{


        public Sort(String url, String imageUrl, String titleStr, boolean isToList) {
            this.url = url;
            this.imageUrl = imageUrl;
            this.titleStr = titleStr;
            this.isToList = isToList;
        }
        public Sort(){}

        private String url; // 跳转url
        private String imageUrl; // 图片url
        private String titleStr; //标题
        private boolean isToList; //是否跳转到分类


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

        public boolean isToList() {
            return isToList;
        }

        public void setToList(boolean toList) {
            isToList = toList;
        }

        public static String toURl(String url){
            return AppInfo.APP_API_SORT + url;
        }


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


    /////////////////////    F1 header 点击事件数据    /////////////////
    public static List<Sort> getOnClickSort(){
        List<Sort> sorts = new ArrayList<>();
        sorts.add(new Sort(Sort.toURl("zaocan/"),null,"早餐",false));
        sorts.add(new Sort(Sort.toURl("wucan/"),null,"午餐",false));
        sorts.add(new Sort(Sort.toURl("wancan/"),null,"晚餐",false));
        sorts.add(new Sort(Sort.toURl("yexiao/"),null,"夜宵",false));
        sorts.add(new Sort(null,null,"菜谱分类",true));
        sorts.add(new Sort("https://m.meishij.net/html5/week.php",null,"人气排行",false));
        return sorts;
    }



}
