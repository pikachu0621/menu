package com.pikachu.menu.home.one.view;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;

import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.home.one.adapter.F1BannerAdapter;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.url.LoadUrl;
import com.to.aboomy.pager2banner.Banner;
import com.to.aboomy.pager2banner.ScaleInTransformer;

import java.util.List;


public class F1HeaderBanner {


    private final Activity activity;
    private final View view;
    private final OnClickHeaderListener onClickHeaderListener;
    private Banner f1HBanner;



    public interface OnClickHeaderListener {
        //轮播点击
        void OnClickBannerItem(View view, int position, HomeF1Data.Banner banner);

        //早晚。。。
        void OnClickHeaderItem(View view, int position, HomeF1Data.Sort sort);

    }


    public F1HeaderBanner(Activity activity, OnClickHeaderListener onClickHeaderListener) {
        this.activity = activity;
        this.onClickHeaderListener = onClickHeaderListener;
        view = LinearLayout.inflate(activity, R.layout.ui_f1_header_banner, null);
        initView();
        init();

    }

    private void init() {
        steMenuOnClick(
                R.id.f1_h_lin1,R.id.f1_h_lin2, R.id.f1_h_lin3, R.id.f1_h_lin4,
                R.id.f1_h_lin5, R.id.f1_h_lin6,R.id.f1_h_lin7,R.id.f1_h_lin8);

        loadBanner();

    }

    //加载轮播
    private void loadBanner() {

        new LoadUrl(activity, AppInfo.APP_API_HOST, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                Tools.showToast(activity,"F1 轮播加载失败");
            }

            @Override
            public void finish(String str) {
                List<HomeF1Data.Banner> bannerData = null;
                try {
                    bannerData = HomeF1Data.getBannerData(str);
                } catch (Exception e) {
                    Tools.showToast(activity,"F1 轮播解析失败");
                    e.printStackTrace();
                    return;
                }
                F1BannerAdapter f1BannerAdapter = new F1BannerAdapter(R.layout.ui_f1_header_item,activity, bannerData, onClickHeaderListener::OnClickBannerItem);
                f1HBanner.setAdapter(f1BannerAdapter);
                f1HBanner.setAutoTurningTime(AppInfo.APP_HOME_F1_AUTO_TIME *1000);
                //开启自动无限轮播
                f1HBanner.startTurning();
                //设置左右页面露出来的宽度及item与item之间的宽度
                f1HBanner.setPageMargin(Tools.dp2px(activity, 20),Tools.dp2px(activity, 20));
                f1HBanner.addPageTransformer(new ScaleInTransformer());

            }
        });

    }

    public void reLoad(){
        loadBanner();
    }




    public void steMenuOnClick(@IdRes int... ids) {

        List<HomeF1Data.Sort> onClickSort = HomeF1Data.getOnClickSort();
        for (int i = 0; i < ids.length; i++) {
            int finalI = i;
            view.findViewById(ids[i]).setOnClickListener(v -> {
                if (onClickHeaderListener != null)
                    onClickHeaderListener.OnClickHeaderItem(v, finalI, onClickSort.get(finalI));
            });
        }
    }

    private void initView() {
        f1HBanner = view.findViewById(R.id.f1_h_banner);
    }



    public Banner getF1HBanner() {
        return f1HBanner;
    }

    public View getView() {
        return view;
    }


}
