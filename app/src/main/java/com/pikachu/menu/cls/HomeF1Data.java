/**
 * 最要用于 F1 的数据整理与封装
 *
 */

package com.pikachu.menu.cls;



import android.text.Html;

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


    //用于分类/点击查看
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
        private String titleStr; //标题
        private float minute = 4.5f; //评分
        private String like ; // 喜欢
        private String browse ; // 浏览

        public HtmlData(String url, String imageUrl, String titleStr, float minute, String like, String browse) {
            this.url = url;
            this.imageUrl = imageUrl;
            this.titleStr = titleStr;
            this.minute = minute;
            this.like = like;
            this.browse = browse;
        }

        public HtmlData(){}

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

        public String getTitleStr() {
            return titleStr;
        }

        public void setTitleStr(String titleStr) {
            this.titleStr = titleStr;
        }

        public float getMinute() {
            return minute;
        }

        public void setMinute(float minute) {
            this.minute = minute;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public String getBrowse() {
            return browse;
        }

        public void setBrowse(String browse) {
            this.browse = browse;
        }

        //con_data_s2_list">


    }

    ////////////////////////////// html 转 实体类 //////////////////////////
    public static List<HtmlData> getHtmlData(String htmlStr) throws Exception{

        String str1 = Tools.cutStr(htmlStr, "con_data_s2w\">", "</ul>");
        assert str1 != null;
        String[] split1 = str1.split("</li>");
        List<HtmlData> htmlData = new ArrayList<>();

        for (String str2 : split1){

            //如果不包含则数据不符合
            if (str2 == null || str2.equals("") || !str2.contains("alt=\""))
                continue;
            //跳转链接
            HtmlData htmlData1 = new HtmlData();

            // 跳转 url
            String url = Tools.cutStr(str2,"href=\"","\"");
            htmlData1.setUrl(url);

            // 菜品大图url
            String imageUrl = Tools.cutStr(str2,"src=\"","\"");
            htmlData1.setImageUrl(imageUrl);

            //菜品名
            String titleStr = Tools.cutStr(str2,"alt=\"","\"");
            htmlData1.setTitleStr(titleStr);


            //喜欢
            String like = Tools.cutStr(str2,"_386.png\">","<");
            htmlData1.setLike(like);

            //浏览
            String browse = Tools.cutStr(str2,"_125.png\">","<");
            htmlData1.setBrowse(browse);

            htmlData.add(htmlData1);

        }
        return htmlData;

    }







    ////////////////////////////   轮播Str 转 实体类     ////////////////

    public static List<Banner> getBannerData(String htmlStr) throws Exception{

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






    /////////////////////   分类Str 转 实体类     ////////////////////////////
    public static List<List<Sort>> getSortData(String htmlStr) throws Exception{

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
        sorts.add(new Sort("3",null,"推荐肉食",2));
        sorts.add(new Sort("2",null,"时令菜",2));
        sorts.add(new Sort(null,null,"菜谱分类",1));
        sorts.add(new Sort("https://m.meishij.net/html5/week.php",null,"人气排行",3));
        return sorts;
    }









    ////菜谱做法实体类
    public static class HtmlMenu {


        private String imageUrl; // 图片url
        private String titleStr; // 标题
        private String postInfoStr; // 发布信息
        private String cookingWay; // 烹饪方法
        private String cookingTaste; // 烹饪口味
        private String cookingTime; // 烹饪时间
        private String cookingHeat; // 烹饪热度
        private String cookingDifficulty; // 烹饪难度
        private String contentStr; // 介绍内容
        private String severalPeople; // 几人
        private List<MainAndAes> mains; // 主料
        private List<MainAndAes> aes; // 辅料
        private List<Step> steps; //步骤
        public static class MainAndAes{
            private String dishName; // 菜名
            private String dishWeight; // 菜重量

            public String getDishName() {
                return dishName;
            }

            public void setDishName(String dishName) {
                this.dishName = dishName;
            }

            public String getDishWeight() {
                return dishWeight;
            }

            public void setDishWeight(String dishWeight) {
                this.dishWeight = dishWeight;
            }
        }
        public static class Step{
            private String stepContent; // 步骤内容
            private String stepImageUrl; // 步骤图片

            public String getStepContent() {
                return stepContent;
            }

            public void setStepContent(String stepContent) {
                this.stepContent = stepContent;
            }

            public String getStepImageUrl() {
                return stepImageUrl;
            }

            public void setStepImageUrl(String stepImageUrl) {
                this.stepImageUrl = stepImageUrl;
            }
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

        public String getPostInfoStr() {
            return postInfoStr;
        }

        public void setPostInfoStr(String postInfoStr) {
            this.postInfoStr = postInfoStr;
        }

        public String getCookingWay() {
            return cookingWay;
        }

        public void setCookingWay(String cookingWay) {
            this.cookingWay = cookingWay;
        }

        public String getCookingTaste() {
            return cookingTaste;
        }

        public void setCookingTaste(String cookingTaste) {
            this.cookingTaste = cookingTaste;
        }

        public String getCookingTime() {
            return cookingTime;
        }

        public void setCookingTime(String cookingTime) {
            this.cookingTime = cookingTime;
        }

        public String getCookingHeat() {
            return cookingHeat;
        }

        public void setCookingHeat(String cookingHeat) {
            this.cookingHeat = cookingHeat;
        }

        public String getCookingDifficulty() {
            return cookingDifficulty;
        }

        public void setCookingDifficulty(String cookingDifficulty) {
            this.cookingDifficulty = cookingDifficulty;
        }

        public String getContentStr() {
            return contentStr;
        }

        public void setContentStr(String contentStr) {
            this.contentStr = contentStr;
        }

        public String getSeveralPeople() {
            return severalPeople;
        }

        public void setSeveralPeople(String severalPeople) {
            this.severalPeople = severalPeople;
        }

        public List<MainAndAes> getMains() {
            return mains;
        }

        public void setMains(List<MainAndAes> mains) {
            this.mains = mains;
        }

        public List<MainAndAes> getAes() {
            return aes;
        }

        public void setAes(List<MainAndAes> aes) {
            this.aes = aes;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }
    }


    /////////////////// 菜谱做法Str数据 转 实体类  ////////////////////////
    public static HtmlMenu getHtmlMenu(String htmlStr,String image,String title) throws Exception{


        HtmlMenu htmlMenu = new HtmlMenu();

        //大图
        htmlMenu.setImageUrl(image);
        //菜谱名
        htmlMenu.setTitleStr(title);

        String info;
        //发布信息
        info = Tools.cutStr(htmlStr, "class=\"posttime\">", "</");
        htmlMenu.setPostInfoStr(info);



        //烹饪方法
        info = Tools.cutStr(htmlStr, "cpargs3\">", "cpargs4\">");
        info = Tools.cutStr(info, "</div>", "</").replace("\t","");
        htmlMenu.setCookingWay(info);

        //烹饪口味
        info = Tools.cutStr(htmlStr, "cpargs2\">", "cpargs3\">");
        info = Tools.cutStr(info, "</div>", "</").replace("\t","");
        htmlMenu.setCookingTaste(info);

        //烹饪时间
        info = Tools.cutStr(htmlStr, "cpargs4\">", "cpargs1\">");
        info = Tools.cutStr(info, "</div>", "</").replace("\t","");
        htmlMenu.setCookingTime(info);

        //烹饪热量
        info = Tools.cutStr(htmlStr, "cpargs1\">", "cpargs5\">");
        info = Tools.cutStr(info, "</div>", "</").replace("\t","");
        htmlMenu.setCookingHeat(info);

        //烹饪难度
        info = Tools.cutStr(htmlStr, "cpargs5\">", null);
        info = Tools.cutStr(info, "</div>", "</").replace("\t","");
        htmlMenu.setCookingDifficulty(info);


        //介绍
        info = Tools.cutStr(htmlStr, "id=\"cpdes\"", "<");
        info = Tools.cutStr(info, ">", "");
        htmlMenu.setContentStr(info);


        //几人份
        info = Tools.cutStr(htmlStr, "主料<span>", "</");
        htmlMenu.setSeveralPeople(info);


        //主料
        info = Tools.cutStr(htmlStr, "c_mtr_ul\">", "c_mtr_t\">");
        assert info != null;
        String[] infoList = info.split("</div>");
        List<HtmlMenu.MainAndAes> mains = new ArrayList<>();
        for (String infoListStr : infoList){

            //如果不包含则数据不符合
            if (infoListStr == null || infoListStr.equals("") || !infoListStr.contains("class=\"t\">"))
                continue;
            HtmlMenu.MainAndAes mainAndAes = new HtmlMenu.MainAndAes();

            //名
            info = Tools.cutStr(infoListStr, "class=\"t\">", "<");
            mainAndAes.setDishName(info);

            //重量
            info = Tools.cutStr(infoListStr, "class=\"a\">", "<");
            mainAndAes.setDishWeight(info);

            mains.add(mainAndAes);
        }
        htmlMenu.setMains(mains); // from mains into  HtmlMenu Class


        //辅料
        info = Tools.cutStr(htmlStr, "id=\"fl_ul\">", "<!--主辅料 end-->");
        assert info != null;
        String[] infoList2 = info.split("</div>");
        List<HtmlMenu.MainAndAes> aes = new ArrayList<>();
        for (String infoListStr : infoList2){

            //如果不包含则数据不符合
            if (infoListStr == null || infoListStr.equals("") || !infoListStr.contains("class=\"t1\">"))
                continue;
            HtmlMenu.MainAndAes mainAndAes = new HtmlMenu.MainAndAes();

            //名
            info = Tools.cutStr(infoListStr, "class=\"t1\">", "<");
            mainAndAes.setDishName(info);

            //重量
            info = Tools.cutStr(infoListStr, "class=\"a\">", "<");
            mainAndAes.setDishWeight(info);

            aes.add(mainAndAes);
        }
        htmlMenu.setAes(aes);// from aes into  HtmlMenu Class




        //步骤
        info = Tools.cutStr(htmlStr, "<!-- 步骤 start-->", "<!-- 步骤 end-->");
        assert info != null;
        String[] infoList3 = info.split(" class=\"stepitem\">");
        List<HtmlMenu.Step> steps = new ArrayList<>();
        for (String infoListStr : infoList3){

            //如果不包含则数据不符合
            if (infoListStr == null || infoListStr.equals("") || !infoListStr.contains("class=\"stepimg\""))
                continue;
            HtmlMenu.Step step = new HtmlMenu.Step();

            //步骤内容
            info = Tools.cutStr(infoListStr, "step_title\">", "</") + " ->   " +
                    Tools.cutStr(infoListStr, "stepdes\">", "</");
            step.setStepContent(info);

            //步骤图片
            info = Tools.cutStr(infoListStr, "src=\"", "\"");
            step.setStepImageUrl(info);

            steps.add(step);
        }
        htmlMenu.setSteps(steps); // from steps into  HtmlMenu Class

        return htmlMenu;
    }



}
