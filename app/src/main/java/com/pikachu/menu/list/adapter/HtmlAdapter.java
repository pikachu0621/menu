package com.pikachu.menu.list.adapter;

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
import com.pikachu.menu.widget.ScoreView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HtmlAdapter extends BaseQuickAdapter<HomeF1Data.HtmlData, BaseViewHolder> {


    private final Context context;
    private final List<HomeF1Data.HtmlData> data;
    private final OnClickHtmlItemListener onClickHtmlItemListener;


    public interface OnClickHtmlItemListener{
        void OnClickItem(View view, int position, HomeF1Data.HtmlData htmlData,  List<HomeF1Data.HtmlData> data);
    }


    public HtmlAdapter(Context context, List<HomeF1Data.HtmlData> data,OnClickHtmlItemListener onClickHtmlItemListener) {
        super(R.layout.ui_html_list_item,data);
        this.context = context;
        this.data = data;
        this.onClickHtmlItemListener = onClickHtmlItemListener;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeF1Data.HtmlData htmlData) {



        baseViewHolder.setText(R.id.html_item_text1,htmlData.getTitleStr());
        baseViewHolder.setText(R.id.html_item_text2,htmlData.getLike());
        baseViewHolder.setText(R.id.html_item_text3,htmlData.getBrowse());
        //ScoreView view = baseViewHolder.getView(R.id.html_item_scoreView1);

        Glide.with(context)
                .load(htmlData.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade(AppInfo.APP_ANIMATION_TIME))
                .into((ImageView) baseViewHolder.getView(R.id.html_item_image1));

    }



}
