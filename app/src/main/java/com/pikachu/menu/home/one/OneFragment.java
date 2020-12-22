package com.pikachu.menu.home.one;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.cls.JsonF1List;
import com.pikachu.menu.home.MainActivity;
import com.pikachu.menu.home.one.adapter.F1RecyclerAdapter;
import com.pikachu.menu.home.one.view.F1HeaderBanner;
import com.pikachu.menu.list.HtmlListActivity;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.base.BaseFragment;
import com.pikachu.menu.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.to.aboomy.pager2banner.Banner;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends BaseFragment implements F1HeaderBanner.OnClickHeaderListener, F1RecyclerAdapter.OnClickF1RecyclerItemListener {


    private final MainActivity activity1;
    private View inflate;
    private SmartRefreshLayout uiRefreshLayout;
    private RecyclerView uiRecycler;
    private AppBarLayout mF1AppBar;
    private View hF1View;
    private RelativeLayout hF1Text1;
    private FragmentActivity activity;
    private F1RecyclerAdapter f1RecyclerAdapter;
    private List<JsonF1List.DataBean.ItemsBean> itemsBean;
    private F1HeaderBanner f1HeaderBanner;
    private int minPager = 0;
    private int page;

    public OneFragment(MainActivity activity) {
        // Required empty public constructor
        activity1 = activity;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_one, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @Override
    protected void initData() {
        uiRefreshLayout.autoRefresh();//自动刷新
        load(true);
    }


    private void init() {
        Tools.setNonHigh(activity, hF1View);

        /*uiRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));*/
        uiRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        uiRefreshLayout.setEnableAutoLoadMore(true);
        uiRefreshLayout.setOnRefreshListener(refreshLayout -> load(true));
        uiRefreshLayout.setOnLoadMoreListener(refreshLayout -> load(false));
    }




    private void load(boolean isUpData) {

        if (isUpData) page = minPager;
        else page++;

        String url = AppInfo.getUrl(1, page);

        new LoadUrl(activity,url, new LoadUrl.OnCall() {


            @Override
            public void error(Exception e) {

                if (isUpData)
                    uiRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
                else {
                    uiRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    page--;
                }

            }

            @Override
            public void finish(String str) {
                //加载成功后 截取需要的字符串
                String json = purifyJson(str);

                JsonF1List jsonF1List = null;
                try {
                    jsonF1List = new Gson().fromJson(json, JsonF1List.class);
                }catch (Exception e){
                    Log.i("text_t"," url "+ url );
                }




                if (jsonF1List !=null && jsonF1List.getData().getItems().size() > 0) {

                    //List<JsonF1List.DataBean.ItemsBean> items = jsonF1List.getData().getItems();
                    if (f1RecyclerAdapter == null || isUpData) {

                        f1RecyclerAdapter = new F1RecyclerAdapter(activity, R.layout.ui_f1_recycler_item, jsonF1List.getData().getItems(), OneFragment.this);

                        f1HeaderBanner = new F1HeaderBanner(activity, OneFragment.this);
                        f1RecyclerAdapter.addHeaderView(f1HeaderBanner.getView());

                        //layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);//防止Item切换
                        uiRecycler.setLayoutManager(new StaggeredGridLayoutManager(AppInfo.APP_HOME_F1_ITEM_NUMBER, StaggeredGridLayoutManager.VERTICAL));
                        uiRecycler.setAdapter(f1RecyclerAdapter);

                    } else
                        f1RecyclerAdapter.addData(jsonF1List.getData().getItems());


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








    public static String purifyJson(String json){

        String[] types = json.split(",\\{");


        for (String s2:types){
            if (s2.contains("img\":[")){
                String s3 = Tools.cutStr(s2, "\"type", null);
                json = json.replace("{\"type" + s3 + ",", "");
            }
        }

        return json.replace("(", "").replace(")", "").replace("\":\"\"","\":null");
    }



  private void initView() {
        uiRefreshLayout = inflate.findViewById(R.id.ui_refreshLayout);
        uiRecycler = inflate.findViewById(R.id.ui_recycler);
        mF1AppBar = inflate.findViewById(R.id.m_f1_appBar);
        hF1View = inflate.findViewById(R.id.h_f1_view);
        hF1Text1 = inflate.findViewById(R.id.h_f1_text1);
    }


    //fragment 隐藏
    @Override
    protected void onInvisible() {
        if (f1HeaderBanner != null && f1HeaderBanner.getF1HBanner() != null)
            f1HeaderBanner.getF1HBanner().stopTurning();
    }


    //fragment 再次显示
    @Override
    protected void onDisplay() {
        if (f1HeaderBanner != null && f1HeaderBanner.getF1HBanner() != null)
            f1HeaderBanner.getF1HBanner().startTurning();

    }


    //////////////// /////////  点击事件 ////////////

    //轮播点击
    @Override
    public void OnClickBannerItem(View view, int position, HomeF1Data.Banner banner) {
        showToast("F1 " + position + " data: " + banner.getUserNameStr());

        Tools.startLookActivity(activity,banner.getUrl(),banner.getImageUrl(),banner.getTitleStr());
    }

    //6个按键点击
    @Override
    public void OnClickHeaderItem(View view, int position, HomeF1Data.Sort sort) {

        showToast("F1 " + position + " data: " + sort.getTitleStr());

        if (sort.getToType() == 1){
            activity1.setPager(1);
            return;
        }
        Intent intent = new Intent(activity, HtmlListActivity.class);
        intent.putExtra(AppInfo.APP_KEY_INTO,sort);
        startActivity(intent);

    }

    //推荐列表点击
    @Override
    public void OnClickItem(View view, int position, JsonF1List.DataBean.ItemsBean itemsBean) {
        showToast("F1 " + position + " data: " + itemsBean.getTitle());

        Tools.startLookActivity(activity,itemsBean.getPath(),itemsBean.getImg(),itemsBean.getTitle());
    }


}