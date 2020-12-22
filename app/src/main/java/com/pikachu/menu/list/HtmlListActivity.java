package com.pikachu.menu.list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.cls.JsonF1List;
import com.pikachu.menu.home.one.OneFragment;
import com.pikachu.menu.home.one.adapter.F1RecyclerAdapter;
import com.pikachu.menu.home.one.view.F1HeaderBanner;
import com.pikachu.menu.list.adapter.HtmlAdapter;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.base.BaseActivity;
import com.pikachu.menu.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import static com.pikachu.menu.home.one.OneFragment.purifyJson;

public class HtmlListActivity extends BaseActivity implements F1RecyclerAdapter.OnClickF1RecyclerItemListener, HtmlAdapter.OnClickHtmlItemListener {

    private View htmlListView;
    private Toolbar htmlListToolbar;
    private SmartRefreshLayout uiRefreshLayout;
    private RecyclerView uiRecycler;
    private HomeF1Data.Sort serializableExtra;
    private int minPager = 1;
    private int page;
    private String url;
    private int toType;
    private String urlTo;
    private F1RecyclerAdapter f1RecyclerAdapter;
    private HtmlAdapter htmlAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_list, R.id.html_list_view);
        initView();
        init();
    }

    private void init() {
        setStatusBarLightMode();
        setSupportActionBar(htmlListToolbar);
        serializableExtra = getSerializableExtra(AppInfo.APP_KEY_INTO, HomeF1Data.Sort.class);
        setHead(true, serializableExtra.getTitleStr(), null, this::finish);


        uiRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        uiRefreshLayout.setEnableAutoLoadMore(true);
        uiRefreshLayout.setOnRefreshListener(refreshLayout -> load(true));
        uiRefreshLayout.setOnLoadMoreListener(refreshLayout -> load(false));


        //加载url
        url = serializableExtra.getUrl();
        //类型 0=html  2=json
        toType = serializableExtra.getToType();


        uiRefreshLayout.autoRefresh();//自动刷新
        load(true);

    }


    private void load(boolean isUpData) {

        if (isUpData) page = minPager;
        else page++;


        if (toType == 2) {
            urlTo = AppInfo.getUrl(url, page);
        } else {
            urlTo = url + "p" + page;
        }


        new LoadUrl(this, urlTo, new LoadUrl.OnCall() {


            @Override
            public void error(Exception e) {

                if (isUpData)
                    uiRefreshLayout.finishRefresh(false);
                else {
                    uiRefreshLayout.finishLoadMore(false);
                    page--;
                }

            }

            @Override
            public void finish(String str) {

                JsonF1List jsonF1List = null;
                List <HomeF1Data.HtmlData> htmlData = null;
                if (toType == 2) {
                    //json
                    //加载成功后 截取需要的字符串
                    String json = purifyJson(str);
                    jsonF1List = new Gson().fromJson(json, JsonF1List.class);
                } else {
                    try {
                        htmlData = HomeF1Data.getHtmlData(str);
                    } catch (Exception e) {
                        showToast("Html 数据解析失败");
                        return;
                    }
                }

                if (toType == 2 ? jsonF1List != null && jsonF1List.getData().getItems().size() > 0 : htmlData!=null && htmlData.size() > 0 ) {

                    if (toType == 2) {

                        if (f1RecyclerAdapter == null || isUpData) {

                            f1RecyclerAdapter = new F1RecyclerAdapter(HtmlListActivity.this, R.layout.ui_f1_recycler_item, jsonF1List.getData().getItems(), HtmlListActivity.this);
                            uiRecycler.setLayoutManager(new StaggeredGridLayoutManager(AppInfo.APP_HOME_F1_ITEM_NUMBER, StaggeredGridLayoutManager.VERTICAL));
                            uiRecycler.setAdapter(f1RecyclerAdapter);
                        } else
                            f1RecyclerAdapter.addData(jsonF1List.getData().getItems());
                    }else {

                        if (htmlAdapter == null || isUpData) {

                            htmlAdapter = new HtmlAdapter(HtmlListActivity.this, htmlData,HtmlListActivity.this);
                            uiRecycler.setLayoutManager(new LinearLayoutManager(HtmlListActivity.this));
                            uiRecycler.setAdapter(htmlAdapter);

                        } else
                            htmlAdapter.addData(htmlData);

                    }

                    if (isUpData) uiRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else uiRefreshLayout.finishLoadMore(true);//结束加载（加载成功）

                } else {
                    if (isUpData) uiRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else {
                        page--;
                        uiRefreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
                    }
                }

            }
        });

    }


    private void initView() {
        htmlListView = findViewById(R.id.html_list_view);
        htmlListToolbar = findViewById(R.id.html_list_toolbar);
        uiRefreshLayout = findViewById(R.id.ui_refreshLayout);
        uiRecycler = findViewById(R.id.ui_recycler);
    }


    //json 列表点击
    @Override
    public void OnClickItem(View view, int position, JsonF1List.DataBean.ItemsBean itemsBean) {

        Tools.startLookActivity(this,itemsBean.getPath(),itemsBean.getImg(),itemsBean.getTitle());
    }

    //html 列表点击
    @Override
    public void OnClickItem(View view, int position, HomeF1Data.HtmlData htmlData, List<HomeF1Data.HtmlData> data) {

        Tools.startLookActivity(this,htmlData.getUrl(),htmlData.getImageUrl(),htmlData.getTitleStr());
    }
}