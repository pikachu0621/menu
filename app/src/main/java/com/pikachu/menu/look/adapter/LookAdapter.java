package com.pikachu.menu.look.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pikachu.menu.R;
import com.pikachu.menu.cls.HomeF1Data;
import com.pikachu.menu.util.app.AppInfo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LookAdapter extends BaseQuickAdapter<HomeF1Data.HtmlMenu.Step, BaseViewHolder> {

    private final Context context;
    private final List<HomeF1Data.HtmlMenu.Step> steps;


    public LookAdapter(Context context, List<HomeF1Data.HtmlMenu.Step> steps) {
        super(R.layout.ui_look_item, steps);
        this.steps = steps;
        this.context = context;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeF1Data.HtmlMenu.Step step) {


        baseViewHolder.setText(R.id.look_item_text1, step.getStepContent());
        Glide.with(context)
                .load(step.getStepImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.look_item_image1));

    }
}


