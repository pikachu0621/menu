package com.pikachu.menu.home.one.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.util.app.AppInfo;
import com.pikachu.menu.widget.QMUIRadiusImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class F1BannerAdapter extends BaseQuickAdapter<HomeF1Data.Banner, BaseViewHolder> {

    private final OnClickBannerItemListener onClickBannerItemListener;
    private final Context context;

    public F1BannerAdapter(int layoutResId, Context context, List<HomeF1Data.Banner> banners, OnClickBannerItemListener onClickBannerItemListener) {
        super(layoutResId,banners);
        this.context = context;
        this.onClickBannerItemListener = onClickBannerItemListener;
    }



    public interface OnClickBannerItemListener{
        void OnClickItem(View view, int position, HomeF1Data.Banner banner);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeF1Data.Banner banner) {
        baseViewHolder.setText(R.id.header_item_text1,banner.getTitleStr());
        baseViewHolder.setText(R.id.header_item_text2,banner.getUserNameStr());

        Glide.with(context)
                .load(banner.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.header_item_image1));

        Glide.with(context)
                .load(banner.getUserImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.header_item_image2));

        baseViewHolder.getView(R.id.header_item_re1).setOnClickListener(v -> onClickBannerItemListener.OnClickItem(v,getItemPosition(banner),banner));
    }
}
