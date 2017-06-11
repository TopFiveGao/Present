package com.administrator.givegiftmodel.interfaces;

import android.view.View;

import com.administrator.givegiftmodel.gson.GiftBean;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface OnRecyclerViewItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view,int position);
}
