package com.administrator.givegiftmodel.gson;

import android.os.Bundle;

import com.administrator.givegiftmodel.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */

public class GiftBean implements Serializable{
    /**
     * RES : 45
     * INFOS : [{"ID":"101","Name":"百年好合","Price":"100","Index":"1","ActFlag":"0"},{"ID":"102","Name":"骑兵","Price":"100","Index":"1","ActFlag":"5"},{"ID":"103","Name":"小怪兽","Price":"100","Index":"1","ActFlag":"0"},{"ID":"104","Name":"雪糕","Price":"100","Index":"1","ActFlag":"0"},{"ID":"105","Name":"赞","Price":"100","Index":"1","ActFlag":"0"},{"ID":"106","Name":"西瓜","Price":"100","Index":"1","ActFlag":"0"},{"ID":"107","Name":"红玫瑰","Price":"100","Index":"1","ActFlag":"0"},{"ID":"108","Name":"熊猫","Price":"200","Index":"1","ActFlag":"0"},{"ID":"109","Name":"666","Price":"200","Index":"1","ActFlag":"0"},{"ID":"110","Name":"流氓蕉","Price":"200","Index":"1","ActFlag":"0"},{"ID":"111","Name":"钻戒","Price":"200","Index":"1","ActFlag":"0"},{"ID":"112","Name":"棒棒糖","Price":"200","Index":"1","ActFlag":"0"},{"ID":"113","Name":"翔","Price":"200","Index":"1","ActFlag":"0"},{"ID":"114","Name":"想你了","Price":"200","Index":"1","ActFlag":"0"},{"ID":"115","Name":"我错了","Price":"200","Index":"1","ActFlag":"0"},{"ID":"116","Name":"鼓掌","Price":"200","Index":"1","ActFlag":"0"},{"ID":"117","Name":"么么","Price":"200","Index":"1","ActFlag":"0"},{"ID":"118","Name":"亲卿","Price":"200","Index":"1","ActFlag":"0"},{"ID":"301","Name":"私人海岛","Price":"500000","Index":"3","ActFlag":"0"},{"ID":"302","Name":"阿斯顿马丁","Price":"500000","Index":"3","ActFlag":"0"},{"ID":"303","Name":"放弃自由","Price":"1000000","Index":"3","ActFlag":"1"},{"ID":"304","Name":"流星雨","Price":"1000000","Index":"3","ActFlag":"1"},{"ID":"305","Name":"大烟花","Price":"1000000","Index":"3","ActFlag":"0"},{"ID":"306","Name":"嫁给我吧","Price":"2000000","Index":"3","ActFlag":"1"},{"ID":"307","Name":"浪漫之夜","Price":"2000000","Index":"3","ActFlag":"1"},{"ID":"308","Name":"宝马","Price":"3000000","Index":"3","ActFlag":"1"},{"ID":"309","Name":"烛光晚餐","Price":"3000000","Index":"3","ActFlag":"1"},{"ID":"310","Name":"爱你一世","Price":"3000000","Index":"3","ActFlag":"1"},{"ID":"311","Name":"兰博基尼","Price":"5000000","Index":"3","ActFlag":"0"},{"ID":"312","Name":"私人飞机","Price":"5000000","Index":"3","ActFlag":"1"},{"ID":"313","Name":"航空母舰","Price":"8000000","Index":"3","ActFlag":"1"},{"ID":"314","Name":"我们结婚吧","Price":"10000000","Index":"3","ActFlag":"1"},{"ID":"315","Name":"别墅","Price":"10000000","Index":"3","ActFlag":"1"},{"ID":"316","Name":"生日快乐","Price":"10000000","Index":"3","ActFlag":"1"},{"ID":"317","Name":"永恒之吻","Price":"20000000","Index":"3","ActFlag":"0"},{"ID":"318","Name":"求婚套装","Price":"30000000","Index":"3","ActFlag":"1"},{"ID":"319","Name":"军演","Price":"30000000","Index":"3","ActFlag":"1"},{"ID":"320","Name":"火山","Price":"30000000","Index":"3","ActFlag":"1"},{"ID":"801","Name":"金币","Price":"10000","Index":"8","ActFlag":"0"},{"ID":"802","Name":"摇钱树","Price":"200000","Index":"8","ActFlag":"0"},{"ID":"803","Name":"金牛","Price":"500000","Index":"8","ActFlag":"0"},{"ID":"804","Name":"聚宝盆","Price":"1000000","Index":"8","ActFlag":"0"},{"ID":"805","Name":"珠宝","Price":"2000000","Index":"8","ActFlag":"0"},{"ID":"806","Name":"金佛","Price":"3000000","Index":"8","ActFlag":"0"},{"ID":"807","Name":"信用金","Price":"10000000","Index":"8","ActFlag":"0"}]
     */

    private String RES;
    private List<Gift> INFOS;

    public String getRES() {
        Bundle bundle=new Bundle();
        bundle.putSerializable("",this);

        return RES;
    }

    public void setRES(String RES) {
        this.RES = RES;
    }

    public List<Gift> getINFOS() {
        return INFOS;
    }

    public void setINFOS(List<Gift> INFOS) {
        this.INFOS = INFOS;
    }

    public static class Gift {
        /**
         * ID : 101
         * Name : 百年好合
         * Price : 100
         * Index : 1
         * ActFlag : 0
         */

        private String ID;
        private String Name;
        private String Price;
        private String Index;
        private String ActFlag;




        /** the number of the gift given*/
        private int number;
        /**the imageResorce of the gift given*/
        private int Icon;

        public int getIcon() {
            return Icon;
        }

        public void setIcon(int icon) {
            Icon = icon;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getIndex() {
            return Index;
        }

        public void setIndex(String Index) {
            this.Index = Index;
        }

        public String getActFlag() {
            return ActFlag;
        }

        public void setActFlag(String ActFlag) {
            this.ActFlag = ActFlag;
        }

    }
}
