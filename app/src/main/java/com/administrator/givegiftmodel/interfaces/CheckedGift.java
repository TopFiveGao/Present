package com.administrator.givegiftmodel.interfaces;

import com.administrator.givegiftmodel.gson.GiftBean;

/**
 * Created by Administrator on 2017/6/9.
 *  用于将RecyclerView中itemView的选中的礼物信息返回通过回调方式传到所在的activity中
 */

public interface CheckedGift {
    void onCheckedGift(GiftBean.Gift checkedGift);
}
