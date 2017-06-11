package com.administrator.givegiftmodel.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.administrator.givegiftmodel.R;
import com.administrator.givegiftmodel.adpters.RecyclerAdapter;
import com.administrator.givegiftmodel.gson.GiftBean;
import com.administrator.givegiftmodel.gson.UserBean;
import com.administrator.givegiftmodel.interfaces.CheckedGift;
import com.administrator.givegiftmodel.interfaces.GiftOption;
import com.administrator.givegiftmodel.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
/**
 * Created by Administrator on 2017/6/6.
 */

public class PageFragment extends Fragment implements OnRecyclerViewItemClickListener,GiftOption{
    /**设置RecyclerView的布局管理器显示的列数*/
    public static final int RECYCLERVIEW_SPANCOUNT = 4;
    /**RecyclerView对象的声明*/
    private RecyclerView recyclerView;
    /**RecyclerView的适配器对象adapter的声明*/
    private RecyclerAdapter adapter;

    private ArrayList<GiftBean.Gift> giftList;
    /**用于动态加载fragment时，为Fragment本身设置的数据源并刷新*/
    public void setData(ArrayList<GiftBean.Gift> giftList){
        this.giftList=giftList;
        if(adapter!=null){
            adapter.setGiftList(giftList);
            adapter.notifyDataSetChanged();
        }
    }
    /**用于根据点击事件传入RecyclerView中暴露的选择礼物接口的实现类对象，即activity对象*/
    private CheckedGift checkedGift;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        checkedGift=(CheckedGift) context;
    }
    private static final String TAG = "PageFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_page,container,false);
        recyclerView= (RecyclerView) convertView.findViewById(R.id.recycler_fragment);
        adaptRecyclerView();
        Log.w(TAG, "onCreateView: " );
        return convertView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w(TAG, "onDestroyView: ");
    }

    private void adaptRecyclerView() {
        adapter = new RecyclerAdapter(getActivity());
        adapter.setOnItemClickListener(this);

        /*设置选择礼物的回调，让activity可以在RecyclerView中进行回调*/
        adapter.setCheckdGift(checkedGift);

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),RECYCLERVIEW_SPANCOUNT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        /*加载activity里为Fragment设置的数据并刷新*/
        if(giftList!=null){
            adapter.setGiftList(giftList);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(View view, int position) {

    }
    @Override
    public void onItemLongClick(View view, int position) {

        Toast.makeText(getActivity(),"longClick "+position,Toast.LENGTH_SHORT).show();
    }


    /**GiftOption接口方法的重写*/

    @Override
    public void sendUser(UserBean toUser) {

    }
    @Override
    public void setOnSendGiftListener(OnSendGiftListener onSelectGiftListener) {

    }
}
