package com.administrator.givegiftmodel.adpters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.givegiftmodel.R;
import com.administrator.givegiftmodel.gson.GiftBean;
import com.administrator.givegiftmodel.interfaces.CheckedGift;
import com.administrator.givegiftmodel.interfaces.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder>{

    private Context context;
    private List<GiftBean.Gift> giftList;
    private OnRecyclerViewItemClickListener itemClickListener;
    /**记录每一个itemView的tag标记*/
    private static String checkedID="-1";
    private static final String TAG = "RecyclerAdapter";
    /**用来将RecyclerView中选中的礼物对象数据传给fragment的接口*/



    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_recycler,parent,false);
        return new ItemHolder(itemView);
    }

    /**暴露给Activity用以传递玩家选择的礼物信息的回调接口*/
    private CheckedGift checkedGift;
    /**设置给Activity中发生其他控件的点击事件后的回调监听**/
    public void setCheckdGift(CheckedGift checkdGift){this.checkedGift=checkdGift;}
    /**用于记录Activity中回调设置过数量属性的选择礼物对象*/
    @Override
    public void onBindViewHolder(final ItemHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.imageView.setImageResource(giftList.get(position).getIcon());
        holder.itemView.setTag(checkedID);

        giftList.get(position).setNumber(1);
        /*根据itemView的tag标记为itemView设置背景图片，此处必须用if-else结构，因为RecyclerView的复用会使滑倒屏幕下的itemView背景变为上面点击过的itemView的背景，造成视觉bug*/
        if(holder.itemView.getTag().toString().equals(giftList.get(position).getID())){
            holder.itemView.setBackgroundResource(R.drawable.item_recycler_selected);
        }else{
            holder.itemView.setBackgroundResource(R.drawable.bg_gift_item);
        }

        holder.name.setText(giftList.get(position).getName());
        holder.value.setText(giftList.get(position).getPrice());

        /*
          RecyclerView 的itemView的点击事件
         */
        if (itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*设置每一个礼物是否被选中，初始为false*/
                    for (GiftBean.Gift gift:giftList){

                    }
                    /*暴露给外部用来传递玩家点击的选择礼物对象**/
                    if(checkedGift!=null){
                        checkedGift.onCheckedGift(giftList.get(position));
                    }else {
                        Log.w(TAG, "onClick: CheckedGift 选择礼物接口初始化失败");
                    }
                    notifyDataSetChanged();
                    itemClickListener.onItemClick(holder.itemView,position);
                    checkedID= giftList.get(position).getID();
                }
            });

            /*
              longClick listener
             */
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    checkedID="-1";
                    notifyDataSetChanged();
                    itemClickListener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (giftList !=null){
            return giftList.size();
        }else{
            return 0;
        }
    }


    class ItemHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView name,value;
        ItemHolder(View itemView) {
            super(itemView);
            this.imageView= (ImageView) itemView.findViewById(R.id.image_gift);
            this.name= (TextView) itemView.findViewById(R.id.name_gift);
            this.value= (TextView) itemView.findViewById(R.id.value_gift);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.itemClickListener=listener;
    }

    public RecyclerAdapter(Context context, List<GiftBean.Gift> giftList) {
        this.context = context;
        this.giftList = giftList;
    }

    public RecyclerAdapter(Context context) {
        this.context = context;

    }
    public List<GiftBean.Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<GiftBean.Gift> giftList) {
        this.giftList = giftList;
    }

}
