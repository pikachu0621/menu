package com.pikachu.menu.home.one.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.cls.JsonF1List;
import com.pikachu.menu.util.app.AppInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class F1RecyclerAdapter extends BaseQuickAdapter<JsonF1List.DataBean.ItemsBean, BaseViewHolder> {


    private final Context context;
    private final OnClickF1RecyclerItemListener onClickF1RecyclerItemListener;


    public interface OnClickF1RecyclerItemListener{
        void OnClickItem(View view, int position, JsonF1List.DataBean.ItemsBean itemsBean);
    }

    public F1RecyclerAdapter( Context context,int layoutResId, List<JsonF1List.DataBean.ItemsBean> itemsBean,OnClickF1RecyclerItemListener onClickF1RecyclerItemListener) {
        super(layoutResId,itemsBean);
        this.context = context;
        this.onClickF1RecyclerItemListener = onClickF1RecyclerItemListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, JsonF1List.DataBean.ItemsBean itemsBean) {

        baseViewHolder.getView(R.id.f1_item_lin1).setOnClickListener(v -> onClickF1RecyclerItemListener.OnClickItem(v,getItemPosition(itemsBean),itemsBean));

        Glide.with(context)
                .load(itemsBean.getImg())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.f1_item_image1));

        Glide.with(context)
                .load(itemsBean.getAuthor().getAvatar_url())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.f1_item_image2));

        baseViewHolder.setText(R.id.f1_item_text1,itemsBean.getTitle());
        baseViewHolder.setText(R.id.f1_item_text2,itemsBean.getAuthor().getNickname());
        baseViewHolder.setText(R.id.f1_item_text3,itemsBean.getViewed_amount());
    }





}
