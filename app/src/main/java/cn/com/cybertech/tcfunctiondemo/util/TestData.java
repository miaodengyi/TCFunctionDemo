package cn.com.cybertech.tcfunctiondemo.util;

import java.util.ArrayList;

import cn.com.cybertech.tcfunctiondemo.bean.HouseBean;
import cn.com.cybertech.tcfunctiondemo.bean.PersonBean;

/**
 * 房屋信息模拟数据
 * Created by miao on 2017/7/20.
 */

public class TestData
{
    private static TestData instance = null;

    public static TestData getInstance()
    {
        if (instance == null)
        {
            instance = new TestData();
        }
        return instance;
    }

    public ArrayList<HouseBean> getHouseData()
    {
        ArrayList<HouseBean> houseBeanArrayList = new ArrayList<>();
        ArrayList<PersonBean> list1 = new ArrayList<>();
        PersonBean personBean = new PersonBean();
        personBean.setId(111111111);
        personBean.setSex("男");
        personBean.setName("张三");
        personBean.setAge("33");
        ArrayList<String> tags = new ArrayList<>();
        tags.add("涉恐");
        tags.add("涉稳");
        tags.add("制毒贩毒");
        tags.add("吸毒人员");
        tags.add("刑事前科");
        tags.add("精神病");
        tags.add("重点上访");
        tags.add("出租车司机");
        tags.add("回，维，藏民");
        tags.add("国保重点人");
        tags.add("涉毒");
        tags.add("犯罪人员");
        tags.add("手机联系人");
        tags.add("货车司机罢工");
        tags.add("未知标签");
        personBean.setTags(tags);
        list1.add(personBean);

        ArrayList<PersonBean> list2 = new ArrayList<>();
        PersonBean personBean2 = new PersonBean();
        personBean2.setId(1222222);
        personBean2.setSex("男");
        personBean2.setName("李四");
        personBean2.setAge("36");
        list2.add(personBean2);

        HouseBean bean101 = new HouseBean();
        bean101.setNum(101);
        bean101.setList(list1);
        houseBeanArrayList.add(bean101);

        HouseBean bean102 = new HouseBean();
        bean102.setNum(102);
        bean102.setList(list2);
        houseBeanArrayList.add(bean102);

        HouseBean bean103 = new HouseBean();
        bean103.setNum(103);
        bean103.setList(list2);
        houseBeanArrayList.add(bean103);

        HouseBean bean104 = new HouseBean();
        bean104.setNum(104);
        bean104.setList(list2);
        houseBeanArrayList.add(bean104);

        HouseBean bean105 = new HouseBean();
        bean105.setNum(105);
        bean105.setList(list2);
        houseBeanArrayList.add(bean105);

        HouseBean bean106 = new HouseBean();
        bean106.setNum(106);
        bean106.setList(list2);
        houseBeanArrayList.add(bean106);

        HouseBean bean107 = new HouseBean();
        bean107.setNum(107);
        bean107.setList(list2);
        houseBeanArrayList.add(bean107);

        HouseBean bean108 = new HouseBean();
        bean108.setNum(108);
        bean108.setList(list2);
        houseBeanArrayList.add(bean108);

        HouseBean bean109 = new HouseBean();
        bean109.setNum(109);
        bean109.setList(list2);
        houseBeanArrayList.add(bean109);

        HouseBean bean111 = new HouseBean();
        bean111.setNum(111);
        bean111.setList(list2);
        houseBeanArrayList.add(bean111);

        HouseBean bean112 = new HouseBean();
        bean112.setNum(112);
        bean112.setList(list1);
        houseBeanArrayList.add(bean112);

        HouseBean bean113 = new HouseBean();
        bean113.setNum(113);
        bean113.setList(list2);
        houseBeanArrayList.add(bean113);

        HouseBean bean114 = new HouseBean();
        bean114.setNum(114);
        bean114.setList(list2);
        houseBeanArrayList.add(bean114);

        HouseBean bean115 = new HouseBean();
        bean115.setNum(115);
        bean115.setList(list2);
        houseBeanArrayList.add(bean115);

        HouseBean bean116 = new HouseBean();
        bean116.setNum(116);
        bean116.setList(list2);
        houseBeanArrayList.add(bean116);

        HouseBean bean117 = new HouseBean();
        bean117.setNum(117);
        bean117.setList(list2);
        houseBeanArrayList.add(bean117);

        HouseBean bean118 = new HouseBean();
        bean118.setNum(118);
        bean118.setList(list2);
        houseBeanArrayList.add(bean118);

        HouseBean bean119 = new HouseBean();
        bean119.setNum(119);
        bean119.setList(list2);
        houseBeanArrayList.add(bean119);

        HouseBean bean121 = new HouseBean();
        bean121.setNum(121);
        bean121.setList(list2);
        houseBeanArrayList.add(bean121);

        HouseBean bean122 = new HouseBean();
        bean122.setNum(122);
        bean122.setList(list2);
        houseBeanArrayList.add(bean122);

        HouseBean bean123 = new HouseBean();
        bean123.setNum(123);
        bean123.setList(list1);
        houseBeanArrayList.add(bean123);

        HouseBean bean124 = new HouseBean();
        bean124.setNum(124);
        bean124.setList(list2);
        houseBeanArrayList.add(bean124);

        HouseBean bean125 = new HouseBean();
        bean125.setNum(125);
        bean125.setList(list2);
        houseBeanArrayList.add(bean125);

        HouseBean bean126 = new HouseBean();
        bean126.setNum(126);
        bean126.setList(list2);
        houseBeanArrayList.add(bean126);

        HouseBean bean127 = new HouseBean();
        bean127.setNum(127);
        bean127.setList(list2);
        houseBeanArrayList.add(bean127);

        HouseBean bean128 = new HouseBean();
        bean128.setNum(128);
        bean128.setList(list2);
        houseBeanArrayList.add(bean128);

        HouseBean bean129 = new HouseBean();
        bean129.setNum(129);
        bean129.setList(list2);
        houseBeanArrayList.add(bean129);

        HouseBean bean131 = new HouseBean();
        bean131.setNum(131);
        bean131.setList(list2);
        houseBeanArrayList.add(bean131);

        HouseBean bean132 = new HouseBean();
        bean132.setNum(132);
        bean132.setList(list2);
        houseBeanArrayList.add(bean132);

        HouseBean bean133 = new HouseBean();
        bean133.setNum(133);
        bean133.setList(list2);
        houseBeanArrayList.add(bean133);

        HouseBean bean134 = new HouseBean();
        bean134.setNum(134);
        bean134.setList(list2);
        houseBeanArrayList.add(bean134);

        HouseBean bean135 = new HouseBean();
        bean135.setNum(135);
        bean135.setList(list2);
        houseBeanArrayList.add(bean135);

        HouseBean bean136 = new HouseBean();
        bean136.setNum(136);
        bean136.setList(list2);
        houseBeanArrayList.add(bean136);

        HouseBean bean137 = new HouseBean();
        bean137.setNum(137);
        bean137.setList(list2);
        houseBeanArrayList.add(bean137);

        HouseBean bean138 = new HouseBean();
        bean138.setNum(138);
        bean138.setList(list2);
        houseBeanArrayList.add(bean138);

        HouseBean bean139 = new HouseBean();
        bean139.setNum(139);
        bean139.setList(list2);
        houseBeanArrayList.add(bean139);

        HouseBean bean141 = new HouseBean();
        bean141.setNum(141);
        bean141.setList(list2);
        houseBeanArrayList.add(bean141);

        HouseBean bean142 = new HouseBean();
        bean142.setNum(142);
        bean142.setList(list2);
        houseBeanArrayList.add(bean142);

        HouseBean bean143 = new HouseBean();
        bean143.setNum(143);
        bean143.setList(list2);
        houseBeanArrayList.add(bean143);

        HouseBean bean144 = new HouseBean();
        bean144.setNum(144);
        bean144.setList(list1);
        houseBeanArrayList.add(bean144);

        HouseBean bean145 = new HouseBean();
        bean145.setNum(145);
        bean145.setList(list2);
        houseBeanArrayList.add(bean145);

        HouseBean bean146 = new HouseBean();
        bean146.setNum(146);
        bean146.setList(list2);
        houseBeanArrayList.add(bean146);

        HouseBean bean147 = new HouseBean();
        bean147.setNum(147);
        bean147.setList(list2);
        houseBeanArrayList.add(bean147);

        HouseBean bean148 = new HouseBean();
        bean148.setNum(148);
        bean148.setList(list2);
        houseBeanArrayList.add(bean148);

        HouseBean bean149 = new HouseBean();
        bean149.setNum(149);
        bean149.setList(list2);
        houseBeanArrayList.add(bean149);

        HouseBean bean151 = new HouseBean();
        bean151.setNum(151);
        bean151.setList(list2);
        houseBeanArrayList.add(bean151);

        HouseBean bean152 = new HouseBean();
        bean152.setNum(152);
        bean152.setList(list2);
        houseBeanArrayList.add(bean152);

        HouseBean bean153 = new HouseBean();
        bean153.setNum(153);
        bean153.setList(list2);
        houseBeanArrayList.add(bean153);

        HouseBean bean154 = new HouseBean();
        bean154.setNum(154);
        bean154.setList(list2);
        houseBeanArrayList.add(bean154);

        HouseBean bean155 = new HouseBean();
        bean155.setNum(155);
        bean155.setList(list1);
        houseBeanArrayList.add(bean155);

        HouseBean bean156 = new HouseBean();
        bean156.setNum(156);
        bean156.setList(list2);
        houseBeanArrayList.add(bean156);

        HouseBean bean157 = new HouseBean();
        bean157.setNum(157);
        bean157.setList(list2);
        houseBeanArrayList.add(bean157);

        HouseBean bean158 = new HouseBean();
        bean158.setNum(158);
        bean158.setList(list2);
        houseBeanArrayList.add(bean158);

        HouseBean bean159 = new HouseBean();
        bean159.setNum(159);
        bean159.setList(list2);
        houseBeanArrayList.add(bean159);

        HouseBean bean161 = new HouseBean();
        bean161.setNum(161);
        bean161.setList(list2);
        houseBeanArrayList.add(bean161);

        HouseBean bean162 = new HouseBean();
        bean162.setNum(162);
        bean162.setList(list2);
        houseBeanArrayList.add(bean162);

        HouseBean bean163 = new HouseBean();
        bean163.setNum(163);
        bean163.setList(list2);
        houseBeanArrayList.add(bean163);

        HouseBean bean164 = new HouseBean();
        bean164.setNum(164);
        bean164.setList(list2);
        houseBeanArrayList.add(bean164);

        HouseBean bean165 = new HouseBean();
        bean165.setNum(165);
        bean165.setList(list2);
        houseBeanArrayList.add(bean165);

        HouseBean bean166 = new HouseBean();
        bean166.setNum(166);
        bean166.setList(list1);
        houseBeanArrayList.add(bean166);

        HouseBean bean167 = new HouseBean();
        bean167.setNum(167);
        bean167.setList(list2);
        houseBeanArrayList.add(bean167);

        HouseBean bean168 = new HouseBean();
        bean168.setNum(168);
        bean168.setList(list2);
        houseBeanArrayList.add(bean168);

        HouseBean bean169 = new HouseBean();
        bean169.setNum(169);
        bean169.setList(list2);
        houseBeanArrayList.add(bean169);

        HouseBean bean171 = new HouseBean();
        bean171.setNum(171);
        bean171.setList(list2);
        houseBeanArrayList.add(bean171);

        HouseBean bean172 = new HouseBean();
        bean172.setNum(172);
        bean172.setList(list2);
        houseBeanArrayList.add(bean172);

        HouseBean bean173 = new HouseBean();
        bean173.setNum(173);
        bean173.setList(list2);
        houseBeanArrayList.add(bean173);

        HouseBean bean174 = new HouseBean();
        bean174.setNum(174);
        bean174.setList(list2);
        houseBeanArrayList.add(bean174);

        HouseBean bean175 = new HouseBean();
        bean175.setNum(175);
        bean175.setList(list2);
        houseBeanArrayList.add(bean175);

        HouseBean bean176 = new HouseBean();
        bean176.setNum(176);
        bean176.setList(list2);
        houseBeanArrayList.add(bean176);

        HouseBean bean177 = new HouseBean();
        bean177.setNum(177);
        bean177.setList(list1);
        houseBeanArrayList.add(bean177);

        HouseBean bean178 = new HouseBean();
        bean178.setNum(178);
        bean178.setList(list2);
        houseBeanArrayList.add(bean178);

        HouseBean bean179 = new HouseBean();
        bean179.setNum(179);
        bean179.setList(list2);
        houseBeanArrayList.add(bean179);

        HouseBean bean181 = new HouseBean();
        bean181.setNum(181);
        bean181.setList(list2);
        houseBeanArrayList.add(bean181);

        HouseBean bean182 = new HouseBean();
        bean182.setNum(182);
        bean182.setList(list2);
        houseBeanArrayList.add(bean182);

        HouseBean bean183 = new HouseBean();
        bean183.setNum(183);
        bean183.setList(list2);
        houseBeanArrayList.add(bean183);

        HouseBean bean184 = new HouseBean();
        bean184.setNum(184);
        bean184.setList(list2);
        houseBeanArrayList.add(bean184);

        HouseBean bean185 = new HouseBean();
        bean185.setNum(185);
        bean185.setList(list2);
        houseBeanArrayList.add(bean185);

        HouseBean bean186 = new HouseBean();
        bean186.setNum(186);
        bean186.setList(list2);
        houseBeanArrayList.add(bean186);

        HouseBean bean187 = new HouseBean();
        bean187.setNum(187);
        bean187.setList(list2);
        houseBeanArrayList.add(bean187);

        HouseBean bean188 = new HouseBean();
        bean188.setNum(188);
        bean188.setList(list1);
        houseBeanArrayList.add(bean188);

        HouseBean bean189= new HouseBean();
        bean189.setNum(189);
        bean189.setList(list2);
        houseBeanArrayList.add(bean189);

        HouseBean bean191 = new HouseBean();
        bean191.setNum(191);
        bean191.setList(list2);
        houseBeanArrayList.add(bean191);

        HouseBean bean192 = new HouseBean();
        bean192.setNum(192);
        bean192.setList(list2);
        houseBeanArrayList.add(bean192);

        HouseBean bean193 = new HouseBean();
        bean193.setNum(193);
        bean193.setList(list2);
        houseBeanArrayList.add(bean193);

        HouseBean bean194 = new HouseBean();
        bean194.setNum(194);
        bean194.setList(list2);
        houseBeanArrayList.add(bean194);

        HouseBean bean195 = new HouseBean();
        bean195.setNum(195);
        bean195.setList(list2);
        houseBeanArrayList.add(bean195);

        HouseBean bean196 = new HouseBean();
        bean196.setNum(196);
        bean196.setList(list2);
        houseBeanArrayList.add(bean196);

        HouseBean bean197 = new HouseBean();
        bean197.setNum(197);
        bean197.setList(list2);
        houseBeanArrayList.add(bean197);

        HouseBean bean198 = new HouseBean();
        bean198.setNum(198);
        bean198.setList(list2);
        houseBeanArrayList.add(bean198);

        HouseBean bean199 = new HouseBean();
        bean199.setNum(199);
        bean199.setList(list1);
        houseBeanArrayList.add(bean199);

        return houseBeanArrayList;
    }

    public ArrayList<String> getListViewSelectData()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("锦绣华城1栋");
        list.add("锦绣华城2栋");
        list.add("锦绣华城3栋");
        list.add("锦绣华城4栋");
        list.add("锦绣华城5栋");
        list.add("锦绣华城6栋");
        list.add("锦绣华城7栋");
        list.add("锦绣华城8栋");
        list.add("锦绣华城9栋");
        list.add("锦绣华城10栋");
        list.add("锦绣华城11栋");
        list.add("锦绣华城12栋");
        list.add("锦绣华城13栋");
        list.add("锦绣华城14栋");
        list.add("锦绣华城15栋");
        return list;
    }
}
