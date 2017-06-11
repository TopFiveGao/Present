package com.administrator.givegiftmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.givegiftmodel.adpters.ViewPagerAdapter;
import com.administrator.givegiftmodel.constants.GiftImageResources;
import com.administrator.givegiftmodel.fragments.PageFragment;
import com.administrator.givegiftmodel.gson.GiftBean;
import com.administrator.givegiftmodel.gson.UserBean;
import com.administrator.givegiftmodel.interfaces.CheckedGift;
import com.administrator.givegiftmodel.interfaces.GiftOption;
import com.administrator.givegiftmodel.utils.OkHttpHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements CheckedGift,GiftOption.OnSendGiftListener{
    private static final int PAGE_ONE=0 ;
    private static final int PAGE_TWO =1 ;
    private static final int PAGE_THREE =2 ;

    @BindView(R.id.test)
    TextView test;

    @BindView(R.id.gift_category)
    TabLayout giftCategory;
    @BindView(R.id.vp_gift)
    ViewPager vpGift;
    @BindView(R.id.choose_hoster)
    Spinner chooseHoster;
    @BindView(R.id.choose_number)
    Spinner chooseNumber;
    @BindView(R.id.bt_give)
    Button btGive;

    private ViewPagerAdapter viewPagerAdapter;
    private PageFragment fragment;
    private UserBean user;
    private int number;
    private static final String TAG = "MainActivity";
    /**通过RecyclerView中的点击回调传入的选中礼物信息*/
    private GiftBean.Gift mGift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpHelper.getInstance().doGetJsonString(URL_IMAGE,handler,MESSAGE_WHAT_JSON);

        ButterKnife.bind(this);
        adaptViewPagerAndTabLayout();
        recordSelectedUser(chooseHoster);
        recordTargetNumber(chooseNumber);
        /**为选择礼物实现设置监听者*/
        setOnSendGiftListener(this);
       /*\********************************* ViewPager滑动监听 ********************************************/
        vpGift.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:

                        fragments.get(2).onDestroyView();
                        break;
                    case 1:
                        fragments.get(0).onDestroyView();
                        fragments.get(2).onDestroyView();
                        break;
                    case 2:
                        fragments.get(0).onDestroyView();
                        fragments.get(1).onDestroyView();
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private static final int MESSAGE_WHAT_JSON = 525;
    private static final int HOT_GIFT_END_INDEX = 17;
    private static final int LUXURY_GIFT_END_INDEX = 37;
    /**
     * Gson解析出的JSON数据对象内部的Gift的集合
     */
    private List<GiftBean.Gift> giftList;
    /**
     * 用于网络请求数据的url地址
     */
    private static final String URL_IMAGE = "http://api.114long.cn/comm/Gift_GetList.ashx";
    private List<Fragment> fragments;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MESSAGE_WHAT_JSON) {
                giftList = OkHttpHelper.getInstance().parseJson(msg.obj.toString(), GiftBean.class);
                /**为每一个网络请求上获得的Gift填充icon信息*/
                if (MainActivity.this.giftList != null) {
                    for (int i = 0; i < giftList.size(); i++) {
                        giftList.get(i).setIcon(GiftImageResources.gifts[i]);
                    }
                }
                /**根据页面为fragment加载不同的礼物数据*/
                if (fragments != null && MainActivity.this.giftList != null) {
                    for (int i = 0; i < fragments.size(); i++) {
                        ArrayList<GiftBean.Gift> gifts = new ArrayList<>();
                        if (i==PAGE_ONE){
                            for (int j = 0; j < HOT_GIFT_END_INDEX + 1; j++) {
                                gifts.add(MainActivity.this.giftList.get(j));
                            }
                        }else if (i==PAGE_TWO){
                            for (int j = HOT_GIFT_END_INDEX + 1; j < LUXURY_GIFT_END_INDEX + 1; j++) {
                                gifts.add(MainActivity.this.giftList.get(j));
                            }
                        }if (i==PAGE_THREE){
                            for (int j = LUXURY_GIFT_END_INDEX + 1; j < giftList.size(); j++) {
                                gifts.add(MainActivity.this.giftList.get(j));
                            }
                        }
                        ((PageFragment) (fragments.get(i))).setData(gifts);
                    }
                }
            }
        }
    };
    /**
     * 为ViewPager适配数据，把fragment装载进ViewPager
     */
    private void adaptViewPagerAndTabLayout() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("热门");
        titles.add("豪华");
        titles.add("金币");
        viewPagerAdapter.setTitleList(titles);
        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragment = new PageFragment();
            fragments.add(fragment);
        }
        viewPagerAdapter.setFragmentList(fragments);
        vpGift.setAdapter(viewPagerAdapter);
        giftCategory.setupWithViewPager(vpGift);
    }

    private GiftOption.OnSendGiftListener sendGiftListener;
    private void setOnSendGiftListener(GiftOption.OnSendGiftListener sendGiftListener){
        this.sendGiftListener=sendGiftListener;
    }
    @OnClick(R.id.bt_give)
    public void onViewClicked() {
        if (sendGiftListener != null) {
            if (mGift != null ) {
                sendGiftListener.sendGift(user, mGift);
                test.setText(mGift.getName()+"   "+mGift.getNumber());

            } else {
                Toast.makeText(getBaseContext(), "recycler中回调传入的礼物为空，即未选择礼物", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "赠送礼物接口未初始化", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *根据用户点击动作记录被选中的主播用户，默认为第一个item中的主播用户
     */
    private void recordSelectedUser(Spinner userSpinner) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_user, R.id.spinner_text, getNameList());
        userSpinner.setAdapter(arrayAdapter);

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        user = new UserBean(0, MainActivity.this.getNameList()[0]);
                        break;
                    case 1:
                        user = new UserBean(1, MainActivity.this.getNameList()[1]);
                        break;
                    case 2:
                        user = new UserBean(2, MainActivity.this.getNameList()[2]);
                        break;
                    case 3:
                        user = new UserBean(3, MainActivity.this.getNameList()[3]);
                        break;
                    case 4:
                        user = new UserBean(4, MainActivity.this.getNameList()[4]);
                        break;
                    case 5:
                        user = new UserBean(5, MainActivity.this.getNameList()[5]);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 根据用户对Spinner的点击事件记录要送的礼物的数量 number，默认数量为第一个item中的数据
     */
    private void recordTargetNumber(final Spinner numberSpinner) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_number, R.id.spinner_text, getNumberList());
        numberSpinner.setAdapter(arrayAdapter);

        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        number = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                    case 1:
                        number = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                    case 2:
                        number = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                    case 3:
                        number = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                    case 4:
                        number = Integer.parseInt(parent.getItemAtPosition(position).toString());
                        break;
                }
                /**——————若没有定向点击动作,则获取的数量number始终是Spinner的第一个item中的数值,选择礼物改变后,即使Spinner的显示内——————
                 * ——————容不在第一个item,也是如此,所以会造成显示的number和实际number不一致的bug,所以此处采取的操作是选择礼物改变——————
                 * ——————后,将spinner初始化到第一个item的位置,使得Spinner显示的item数值和实际数值相同,并要求用户进行定向点击动作——————
                 * */
                if (mGift!=null){
                    mGift.setNumber(number);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private String[] getNameList() {
        String[] host = {"熊猫主播", "斗鱼主播", "龙珠TV", "全民TV", "虎牙直播", "YY直播"};
        return host;
    }
    private String[] getNumberList() {
        String[] numbers = new String[]{"1", "10", "100", "520", "1314"};
        return numbers;
    }
    /**Fragment中RecyclerView发生点击回调事件时，传递玩家选中礼物的方法*/
    @Override
    public void onCheckedGift(GiftBean.Gift checkedGift) {
        this.mGift=checkedGift;
        /**将Spinner中的item位置初始化为最初位置*/
        chooseNumber.setSelection(0);
    }
    @Override
    public void sendGift(UserBean user, GiftBean.Gift gift) {
        if(gift!=null){
            Toast.makeText(this,"主播名 "+user.getName()+" 礼物名 "+gift.getName()+" 数量"+gift.getNumber(),Toast.LENGTH_SHORT).show();

        }
    }

}
