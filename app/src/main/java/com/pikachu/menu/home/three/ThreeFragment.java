package com.pikachu.menu.home.three;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.cls.JsonF1List;
import com.pikachu.menu.home.one.OneFragment;
import com.pikachu.menu.home.one.adapter.F1RecyclerAdapter;
import com.pikachu.menu.home.one.view.F1HeaderBanner;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.base.BaseFragment;
import com.pikachu.menu.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import static com.pikachu.menu.home.one.OneFragment.purifyJson;


public class ThreeFragment extends BaseFragment implements F1RecyclerAdapter.OnClickF1RecyclerItemListener {


    private View inflate;
    private RelativeLayout mF2Re1;
    private View mF2View;
    private LinearLayout mF2Lin1;
    private TextView mF2Text1;
    private ImageView mF2Image1;
    private FragmentActivity activity;
    private RefreshLayout uiRefreshLayout;
    private RecyclerView uiRecycler;
    private View hF1View;
    private int minPager = 5;
    private int page;
    private F1RecyclerAdapter f1RecyclerAdapter;

    public ThreeFragment() {
        // Required empty public constructor
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_three, container, false);
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

        String url = AppInfo.getUrl(5, page);

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

                        f1RecyclerAdapter = new F1RecyclerAdapter(activity, R.layout.ui_f1_recycler_item, jsonF1List.getData().getItems(), ThreeFragment.this);
                        f1RecyclerAdapter.addHeaderView(LinearLayout.inflate(activity, R.layout.ui_f3_banner, null));
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









    private void initView() {
        uiRefreshLayout = inflate.findViewById(R.id.ui_refreshLayout);
        uiRecycler = inflate.findViewById(R.id.ui_recycler);
        hF1View = inflate.findViewById(R.id.m_f2_view);
    }





    //////////////// /////////  点击事件 ////////////


    //推荐列表点击
    @Override
    public void OnClickItem(View view, int position, JsonF1List.DataBean.ItemsBean itemsBean) {
        showToast("F1 " + position + " data: " + itemsBean.getTitle());
        Tools.startLookActivity(activity , itemsBean.getPath(),itemsBean.getImg(),itemsBean.getTitle());
    }





}