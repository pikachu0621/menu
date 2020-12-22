package com.pikachu.menu.list;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.pikachu.menu.R;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.base.BaseActivity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import pl.droidsonroids.gif.GifImageView;

public class HtmlListActivity extends BaseActivity {

    private View htmlListView;
    private Toolbar htmlListToolbar;
    private SmartRefreshLayout uiRefreshLayout;
    private RecyclerView uiRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_html_list, R.id.html_list_view);
        initView();
        init();
    }

    private void init() {
        setSupportActionBar(htmlListToolbar);
        setHead(true, getStringExtra(AppInfo.APP_KEY_INTO), null, this::finish);



    }


    private void initView() {
        htmlListView = findViewById(R.id.html_list_view);
        htmlListToolbar = findViewById(R.id.html_list_toolbar);
        uiRefreshLayout = findViewById(R.id.ui_refreshLayout);
        uiRecycler = findViewById(R.id.ui_recycler);
    }
}