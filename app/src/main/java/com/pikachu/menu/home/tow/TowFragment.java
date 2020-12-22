package com.pikachu.menu.home.tow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.home.tow.adapter.F2Adapter;
import com.pikachu.menu.list.HtmlListActivity;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.base.BaseFragment;
import com.pikachu.menu.util.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class TowFragment extends BaseFragment implements F2Adapter.OnClickF2ItemListener {


    private FragmentActivity activity;
    private View inflate;
    private View mF2View;
    private TextView mF2Text1;
    private ImageView mF2Image1;
    private RecyclerView mF2Recycler;
    private SmartRefreshLayout uiRefreshLayout;
    private GifImageView gifView;
    private RecyclerView uiRecycler;
    private F2Adapter f2AdapterLift;
    private F2Adapter f2AdapterRight;
    private String url;
    private boolean isLift;
    private int position1 = 0;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_tow, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @Override
    protected void initData() {
        uiRefreshLayout.autoRefresh(); //自动刷新
        //加载标题
        loadTitle();
    }

    private void init() {
        Tools.setNonHigh(activity, mF2View);

        uiRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        uiRefreshLayout.setEnableAutoLoadMore(true);
        uiRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        uiRefreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
        uiRefreshLayout.setOnRefreshListener(refreshLayout -> loadTitle());
        //uiRefreshLayout.setOnLoadMoreListener(refreshLayout -> loadTitle());
        uiRecycler.setBackgroundColor(activity.getResources().getColor(R.color.white_0));
        uiRefreshLayout.setBackgroundColor(activity.getResources().getColor(R.color.white_0));
        this.url = AppInfo.APP_API_SORT_ONE;
        this.isLift = true;
    }

    //加载标题
    private void loadTitle() {

        new LoadUrl(activity, url, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                showToast("f2 " + e.getMessage());
                uiRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }

            @Override
            public void finish(String str) {

                List<List<HomeF1Data.Sort>> sortData = null;
                try {
                    sortData = HomeF1Data.getSortData(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    showToast("f2 数据解析失败");
                    uiRefreshLayout.finishRefresh(false);
                    return;
                }
                if (isLift) {
                    if (f2AdapterLift == null) {
                        f2AdapterLift = new F2Adapter(activity, sortData.get(0), true, TowFragment.this);
                        mF2Recycler.setLayoutManager(new LinearLayoutManager(activity));
                        mF2Recycler.setAdapter(f2AdapterLift);
                    } else
                        f2AdapterLift.replaceData(sortData.get(0));
                }
                if (f2AdapterRight == null) {
                    f2AdapterRight = new F2Adapter(activity, sortData.get(1), false, TowFragment.this);
                    uiRecycler.setLayoutManager(new GridLayoutManager(activity, 3));
                    uiRecycler.setAdapter(f2AdapterRight);

                } else {
                    f2AdapterRight.replaceData(sortData.get(1));
                }


                uiRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）

            }
        });

    }


    private void initView() {
        mF2View = inflate.findViewById(R.id.m_f2_view);
        mF2Text1 = inflate.findViewById(R.id.m_f2_text1);
        mF2Image1 = inflate.findViewById(R.id.m_f2_image1);
        mF2Recycler = inflate.findViewById(R.id.m_f2_recycler);
        uiRefreshLayout = inflate.findViewById(R.id.ui_refreshLayout);
        uiRecycler = inflate.findViewById(R.id.ui_recycler);
    }


    //// lift 点击事件
    @Override
    public void OnClickLiftItem(View view, int position, HomeF1Data.Sort banner, List<HomeF1Data.Sort> sorts) {
        if (position1 != position) {
            uiRefreshLayout.autoRefresh(); //自动刷新
            //加载标题
            this.url = banner.getUrl();
            this.isLift = false;
            for (HomeF1Data.Sort sort :
                    sorts)
                sort.setSelected(false);
            banner.setSelected(true);
            loadTitle();
            this.position1 = position;
            f2AdapterLift.notifyDataSetChanged();
        }
    }

    // right 没有图片的点击事件
    @Override
    public void OnClickRightOntImageItem(View view, int position, HomeF1Data.Sort sort, List<HomeF1Data.Sort> sorts) {
        showToast(sort.getUrl());
    }

    // right 有图片的点击事件
    @Override
    public void OnClickRightImageItem(View view, int position, HomeF1Data.Sort sort, List<HomeF1Data.Sort> sorts) {
        showToast(sort.getUrl());
        if (sort.getUrl().contains(".php?")) {
            showToast("此URL还没适配");
            return;
        }
        Intent intent = new Intent(activity, HtmlListActivity.class);
        intent.putExtra(AppInfo.APP_KEY_INTO, sort);
        startActivity(intent);
    }




}