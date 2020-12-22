package com.pikachu.menu.home.tow.adapter;


import android.content.Context;
import android.view.View;
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

public class F2Adapter extends BaseQuickAdapter<HomeF1Data.Sort, BaseViewHolder> {

    private final OnClickF2ItemListener onClickF2ItemListener;
    private final Context context;
    private final boolean isLift;
    private final List<HomeF1Data.Sort> sorts;

    public F2Adapter( Context context, List<HomeF1Data.Sort> sorts,boolean isLift, OnClickF2ItemListener onClickF2ItemListener) {
        super(R.layout.ui_f2_recycler_item,sorts);
        this.sorts = sorts;
        this.context = context;
        this.onClickF2ItemListener = onClickF2ItemListener;
        this.isLift = isLift;
    }



    public interface OnClickF2ItemListener{
        //点击分类右边
        void OnClickLiftItem(View view, int position, HomeF1Data.Sort sort, List<HomeF1Data.Sort> sorts);
        //点击分类左边不带头像的
        void OnClickRightOntImageItem(View view, int position, HomeF1Data.Sort sort ,List<HomeF1Data.Sort> sorts);
        //点击分类左边带头像的
        void OnClickRightImageItem(View view, int position, HomeF1Data.Sort sort, List<HomeF1Data.Sort> sorts);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeF1Data.Sort sort) {

        if (isLift) {
            gone(baseViewHolder, 0);
            baseViewHolder.setText(R.id.f2_item_text1,sort.getTitleStr());
            baseViewHolder.setBackgroundColor(R.id.f2_item_text1, sort.isSelected() ? context.getResources().getColor(R.color.white_0) : context.getResources().getColor(R.color.white));
            baseViewHolder.getView(R.id.f2_item_text1).setOnClickListener(v -> onClickF2ItemListener.OnClickLiftItem(v,getItemPosition(sort),sort, this.sorts));
        }else {


            if (sort.getImageUrl() != null){

                gone(baseViewHolder, 1);
                baseViewHolder.setText(R.id.f2_item_text2,sort.getTitleStr());
                Glide.with(context)
                        .load(sort.getImageUrl())
                        .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                        .into((ImageView) baseViewHolder.getView(R.id.f2_item_image1));


                baseViewHolder.getView(R.id.f2_item_lin1).setOnClickListener(v -> onClickF2ItemListener.OnClickRightImageItem(v,getItemPosition(sort),sort,this.sorts ));

            } else{
                gone(baseViewHolder, 2);
                baseViewHolder.setText(R.id.f2_item_text3,sort.getTitleStr());
                baseViewHolder.getView(R.id.f2_item_text3).setOnClickListener(v -> onClickF2ItemListener.OnClickRightOntImageItem(v,getItemPosition(sort),sort, this.sorts));
            }


        }
    }

    private void gone(BaseViewHolder baseViewHolder,int i) {
        baseViewHolder.setGone(R.id.f2_item_text1,!(i==0));
        baseViewHolder.setGone(R.id.f2_item_lin1,!(i==1));
        baseViewHolder.setGone(R.id.f2_item_text3,!(i==2));
    }







}
