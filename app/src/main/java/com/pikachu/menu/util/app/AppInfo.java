package com.pikachu.menu.util.app;

public class AppInfo {






    ///////////// app配置 /////////////////




    //首页
    public static final long APP_HOME_F1_AUTO_TIME = 6;             //首页轮播时间                   可选值   (0,Max]  单位 秒
    public static final int APP_HOME_F1_ITEM_NUMBER = 2;            //首页每行几个item               可选值   [0,max]


    //全局
    public static final int APP_ANIMATION_TIME = 600;  //动画时间




    ///////////////  KEY //////////////
    public static final String APP_KEY_INTO =  "APP_KEY_INTO";


    ////////////    API     ///////
    //用于加载图片列表
    public static final String APP_API_HOST = "https://m.meishij.net";
    public static final String APP_API_SORT =  APP_API_HOST + "/fenlei/";
    public static final String APP_API_IMAGE_LIST =  APP_API_HOST +"/ajax/index_search_new.php?type={type}&page={page}";
    public static final String APP_API_SORT_ONE =  APP_API_HOST +"/caipudaquan/";
    public static final String APP_API_SEARCH_LIST =  APP_API_HOST +"/ajax/search_list_test.php?tab=3&words={words}&page={page}";




    public static String getUrl(int type, int page) {
        return APP_API_IMAGE_LIST.replace("{type}",""+type).replace("{page}",""+page);
    }

    public static String getSearchUrl(String words, int page) {
        return APP_API_SEARCH_LIST.replace("{words}",words).replace("{page}",""+page);
    }


    public static String getUrl(String type, int page) {
        return getUrl(Integer.parseInt(type), page);
    }








}
