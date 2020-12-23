package com.pikachu.menu.look.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.util.app.AppInfo;


public class LookHeaderBanner {


    private final Activity activity;
    private final View view;
    private View item;
    private final HomeF1Data.HtmlMenu htmlMenu;
    private ImageView lookBannerImage1;
    private TextView lookText1;
    private TextView lookText2;
    private TextView lookText3;
    private TextView lookText4;
    private TextView lookText5;
    private TextView lookText6;
    private TextView lookText7;
    private TextView lookText8;
    private TextView lookText9;
    private LinearLayout lookLin1;
    private LinearLayout lookLin2;


    public LookHeaderBanner(Activity activity, HomeF1Data.HtmlMenu htmlMenu) {
        this.activity = activity;
        this.htmlMenu = htmlMenu;
        view = LinearLayout.inflate(activity, R.layout.ui_look_banner, null);
        initView();
        init();
    }


    //数据展示
    private void init() {



        //图片
        Glide.with(activity)
                .load(htmlMenu.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into(lookBannerImage1);

        //标题
        lookText1.setText(htmlMenu.getTitleStr());
        //菜谱信息
        lookText2.setText(htmlMenu.getPostInfoStr());
        //烹饪方法
        lookText3.setText(htmlMenu.getCookingWay());
        //烹饪口味
        lookText4.setText(htmlMenu.getCookingTaste());
        //烹饪时间
        lookText5.setText(htmlMenu.getCookingTime());
        //烹饪热量
        lookText6.setText(htmlMenu.getCookingHeat());
        //烹饪难度
        lookText7.setText(htmlMenu.getCookingDifficulty());
        //介绍
        lookText8.setText(htmlMenu.getContentStr());
        //几人份
        lookText9.setText(htmlMenu.getSeveralPeople());

        //添加主料
        for (HomeF1Data.HtmlMenu.MainAndAes mainAndAes : htmlMenu.getMains()){
            item = LinearLayout.inflate(activity, R.layout.ui_look_banner_item, null); // 得到 主/辅 item布局
            ((TextView) item.findViewById(R.id.look_b_text1)).setText(mainAndAes.getDishName());
            ((TextView) item.findViewById(R.id.look_b_text2)).setText(mainAndAes.getDishWeight());
            lookLin1.addView(item);
        }

        //添加辅料
        for (HomeF1Data.HtmlMenu.MainAndAes mainAndAes : htmlMenu.getAes()){
            item = LinearLayout.inflate(activity, R.layout.ui_look_banner_item, null); // 得到 主/辅 item布局
            ((TextView) item.findViewById(R.id.look_b_text1)).setText(mainAndAes.getDishName());
            ((TextView) item.findViewById(R.id.look_b_text2)).setText(mainAndAes.getDishWeight());
            lookLin2.addView(item);
        }

    }


    public View getView() {
        return view;
    }


    private void initView() {
        lookBannerImage1 = view.findViewById(R.id.look_banner_image1);

        lookText1 = view.findViewById(R.id.look_text1);
        lookText2 = view.findViewById(R.id.look_text2);
        lookText3 = view.findViewById(R.id.look_text3);
        lookText4 = view.findViewById(R.id.look_text4);
        lookText5 = view.findViewById(R.id.look_text5);
        lookText6 = view.findViewById(R.id.look_text6);
        lookText7 = view.findViewById(R.id.look_text7);
        lookText8 = view.findViewById(R.id.look_text8);
        lookText9 = view.findViewById(R.id.look_text9);

        lookLin1 = view.findViewById(R.id.look_lin1);  // 主料
        lookLin2 = view.findViewById(R.id.look_lin2); // 辅料
    }
}
