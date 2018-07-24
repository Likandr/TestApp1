package com.likandr.condortestapp.ui.main.some_data.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp._common.base.api.NetConst;
import com.likandr.condortestapp._common.misc.PicassoTrustAll;
import com.likandr.condortestapp.data._models.SomeData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SomeDataItem extends CardView {

    @BindView(R.id.iv_pic)  ImageView tvPic;
    @BindView(R.id.tv_name) TextView tvName;

    public SomeDataItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(SomeData someData) {
        PicassoTrustAll.getInstance(getContext()).load(NetConst.PIC_RANDOM).into(tvPic);
        tvName.setText(someData.getName());
    }
}
