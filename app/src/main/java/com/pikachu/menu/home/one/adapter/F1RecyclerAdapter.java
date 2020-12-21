package com.pikachu.menu.home.one.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.JsonF1List;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class F1RecyclerAdapter extends BaseQuickAdapter<JsonF1List.DataBean.ItemsBean, BaseViewHolder> {


    public F1RecyclerAdapter(int layoutResId , List<JsonF1List.DataBean.ItemsBean> itemsBean) {
        super(layoutResId,itemsBean);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, JsonF1List.DataBean.ItemsBean itemsBean) {


    }









}
