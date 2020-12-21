package com.pikachu.menu.home.one;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.appbar.AppBarLayout;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.cls.JsonF1List;
import com.pikachu.menu.home.one.adapter.F1RecyclerAdapter;
import com.pikachu.menu.home.one.view.F1HeaderBanner;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.util.app.Tools;
import com.pikachu.menu.util.base.BaseFragment;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.to.aboomy.pager2banner.Banner;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends BaseFragment implements F1HeaderBanner.OnClickHeaderListener {


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

    public OneFragment() {
        // Required empty public constructor


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


    }


    private void init() {
        Tools.setNonHigh(activity, hF1View);

        itemsBean = new ArrayList<>();
        f1RecyclerAdapter = new F1RecyclerAdapter(R.layout.ui_bottom_nar, itemsBean);

        f1HeaderBanner = new F1HeaderBanner(activity,OneFragment.this);
        f1RecyclerAdapter.addHeaderView(f1HeaderBanner.getView());
        uiRecycler.setAdapter(f1RecyclerAdapter);
        uiRecycler.setLayoutManager(new StaggeredGridLayoutManager(AppInfo.APP_HOME_F1_ITEM_NUMBER,StaggeredGridLayoutManager.VERTICAL));

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
        if(f1HeaderBanner != null && f1HeaderBanner.getF1HBanner() != null)
            f1HeaderBanner.getF1HBanner().stopTurning();
    }


    //fragment 再次显示
    @Override
    protected void onDisplay() {
        if(f1HeaderBanner != null && f1HeaderBanner.getF1HBanner() != null)
            f1HeaderBanner.getF1HBanner().startTurning();

    }







    //////////////// /////////  点击事件 ////////////

    //轮播点击
    @Override
    public void OnClickBannerItem(View view, int position, HomeF1Data.Banner banner) {
        showToast("F1 "+ position + " data: "+ banner.getUserNameStr() );
    }

    //6个按键点击
    @Override
    public void OnClickHeaderItem(View view, int position, HomeF1Data.Sort sort) {
        showToast("F1 "+ position + " data: "+ sort.getTitleStr() );
    }

    //推荐列表点击



}