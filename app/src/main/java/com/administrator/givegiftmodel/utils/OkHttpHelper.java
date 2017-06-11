package com.administrator.givegiftmodel.utils;

import android.os.Handler;
import android.os.Message;

import com.administrator.givegiftmodel.gson.GiftBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/9.
 */

public class OkHttpHelper {

        private static OkHttpHelper okHttpHelper=null;
        public synchronized static OkHttpHelper getInstance(){
            if (okHttpHelper==null){
                okHttpHelper=new OkHttpHelper();
            }
            return okHttpHelper;
        }
        private OkHttpHelper(){}
        /**用OkHttpClient从网络获取字符串数据，获取完成后，由传入的handler对象进行通知，并把字符串数据发送到主线程*/
        public void doGetJsonString(String url, final Handler handler, final int what){
            OkHttpClient okHttpClient =new OkHttpClient();
            okHttpClient.newCall(new Request.Builder().get().url(url).build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String originString=response.body().string();
                    Message message= Message.obtain();
                    message.what=what;
                    originString=originString.substring(originString.indexOf("(")+1,originString.length()-1);
                    message.obj=originString;
                    handler.sendMessage(message);
                }
            });
        }
        /**把JSON字符串数据解析成JSON实体类对象,并返回所需要的礼物信息数据集合*/
        public List<GiftBean.Gift> parseJson(String json, Class<GiftBean> bean){
            Gson gson=new Gson();
            GiftBean giftBean = gson.fromJson(json, bean);
            List<GiftBean.Gift> infos = giftBean.getINFOS();
            return infos;
        }


}
