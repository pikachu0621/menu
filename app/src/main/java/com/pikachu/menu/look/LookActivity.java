package com.pikachu.menu.look;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.base.BaseActivity;
import com.pikachu.menu.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import pl.droidsonroids.gif.GifImageView;

public class LookActivity extends BaseActivity {


    private Toolbar htmlListToolbar;
    private SmartRefreshLayout uiRefreshLayout;
    private RecyclerView uiRecycler;
    private HomeF1Data.Sort sort;
    private String url;
    private AppBarLayout lookAppbar;
    private ImageView lookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look, R.id.look_view);


        initView();
        init();

    }

    private void init() {
        setStatusBarLightMode();
        setSupportActionBar(htmlListToolbar);
        sort = getSerializableExtra(AppInfo.APP_KEY_INTO, HomeF1Data.Sort.class);
        setHead(true, sort.getTitleStr(), null, this::finish);


        uiRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        uiRefreshLayout.setEnableAutoLoadMore(true);
        uiRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        uiRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        //uiRefreshLayout.setOnRefreshListener(refreshLayout -> load());

        url = sort.getUrl();

        Glide.with(this)
                .load(sort.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into(lookImage);

    }

    private void load() {


        new LoadUrl(this, url, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                showToast("Look " + e.getMessage());
                uiRefreshLayout.finishRefresh(false);
            }

            @Override
            public void finish(String str) {


                uiRefreshLayout.finishRefresh(true);
            }
        });


    }


    private void initView() {
        htmlListToolbar = findViewById(R.id.html_list_toolbar);
        uiRefreshLayout = findViewById(R.id.ui_refreshLayout);
        uiRecycler = findViewById(R.id.ui_recycler);
        lookAppbar = findViewById(R.id.look_appbar);
        lookImage = findViewById(R.id.look_image);
    }
}