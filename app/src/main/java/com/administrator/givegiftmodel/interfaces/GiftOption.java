package com.administrator.givegiftmodel.interfaces;

import com.administrator.givegiftmodel.gson.GiftBean;
import com.administrator.givegiftmodel.gson.UserBean;

/**
 * Created by Administrator on 2017/6/8.
 */

public interface GiftOption {
    /**
     * 设置接收礼物的用户
     *
     * @param toUser
     */
    void sendUser(UserBean toUser);
    /**
     * 设置选择礼物的监听
     *
     * @param sendGiftListener
     */
    void setOnSendGiftListener(OnSendGiftListener sendGiftListener);

    interface OnSendGiftListener {
        void sendGift(UserBean user, GiftBean.Gift gift);
    }

}
