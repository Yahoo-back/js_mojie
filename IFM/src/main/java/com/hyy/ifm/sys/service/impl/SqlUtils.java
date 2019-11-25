package com.hyy.ifm.sys.service.impl;

import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.util.DateUtils;
import com.hyy.ifm.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlUtils {

    public static com.hyy.ifm.sys.pojo.Cnds apCnd(com.hyy.ifm.sys.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_code_cnd()))) {
            cnd.append(" and isl.user_code like '%" + StringUtil.nvl(cnds.getRows().getUser_code_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getIdentity_card_cnd()))) {
            cnd.append(" and isu.IDENTITY_CARD like '%" + StringUtil.nvl(cnds.getRows().getIdentity_card_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_name_cnd()))) {
            cnd.append(" and isu.user_name like '%" + StringUtil.nvl(cnds.getRows().getUser_name_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSex_cnd()))) {
            cnd.append(" and isu.sex = '" + StringUtil.nvl(cnds.getRows().getSex_cnd()) + "' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and isu.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }
    public static com.hyy.ifm.sys.pojo.Cnds apCnd6(com.hyy.ifm.sys.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_code_cnd()))) {
            cnd.append(" and isl.user_code like '%" + StringUtil.nvl(cnds.getRows().getUser_code_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getIdentity_card_cnd()))) {
            cnd.append(" and isu.IDENTITY_CARD like '%" + StringUtil.nvl(cnds.getRows().getIdentity_card_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_name_cnd()))) {
            cnd.append(" and isu.user_name like '%" + StringUtil.nvl(cnds.getRows().getUser_name_cnd()) + "%' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSex_cnd()))) {
            cnd.append(" and isu.sex = '" + StringUtil.nvl(cnds.getRows().getSex_cnd()) + "' ");
        }
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and isu.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        if (!cnds.getUserCode().equals("13")) {
            cnd.append(" and isl.openLgnId = '" + StringUtil.nvl(cnds.getUserCode()) + "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }
    public static com.hyy.ifm.product.pojo.Cnds apCnd1(com.hyy.ifm.product.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据商品名称模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dp.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据分类查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getClassify_cnd()))) {
            cnd.append(" and dpc.id = '" + StringUtil.nvl(cnds.getRows().getClassify_cnd()) + "' ");
        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and dp.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据是否是首页热门查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getIs_hot_cnd()))) {
            cnd.append(" and dp.is_hot = '" + StringUtil.nvl(cnds.getRows().getIs_hot_cnd()) + "' ");
        }
        //根据位置查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getPosition_cnd()))) {
            cnd.append(" and dp.position = '" + StringUtil.nvl(cnds.getRows().getPosition_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dp.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dp.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.product.pojo.Cnds apCnd2(com.hyy.ifm.product.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据名称模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dpc.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据是否被使用
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and dpc.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据是否首页展示
        if (!"".equals(StringUtil.nvl(cnds.getRows().getIs_home_cnd()))) {
            cnd.append(" and dpc.is_home = '" + StringUtil.nvl(cnds.getRows().getIs_home_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dpc.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dpc.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.business.pojo.Cnds apCnd1(com.hyy.ifm.business.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据名字模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dic.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据手机号查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getMobile_cnd()))) {
            cnd.append(" and dic.mobile like '%" + StringUtil.nvl(cnds.getRows().getMobile_cnd()) + "%' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dic.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dic.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd1(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据类型查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getClassify_cnd()))) {
            cnd.append(" and dvl.classify = '" + StringUtil.nvl(cnds.getRows().getClassify_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dvl.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())+ "' " );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dvl.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())+ "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds clCnd1(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())+ "' " );
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd())+ "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd2(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据商品名称查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getProduct_name_cnd()))) {
            cnd.append(" and dp.name like '%" + StringUtil.nvl(cnds.getRows().getProduct_name_cnd()) + "%' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dpvl.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +":00:00' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dpvl.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +":00:00' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd3(com.hyy.ifm.data.pojo.Cnds cnds) throws ParseException {
        StringBuffer cnd = new StringBuffer();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
        String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());

        if(StringUtil.isBlank(daysFrom) && StringUtil.isBlank(daysTo)) {
            //页面载入 默认展示7小时内的折线图
            cnd.append(" SELECT date_sub(date_format(now(),'%y-%m-%d %H'), interval 0 HOUR) as click_date union all ");
            for(int i=1; i<7; i++) {
                cnd.append(" SELECT date_sub(date_format(now(),'%y-%m-%d %H'), interval " + i +" HOUR) as click_date union all ");
            }
            cnd.append(" SELECT date_sub(date_format(now(),'%y-%m-%d %H'), interval 7 HOUR) as click_date ");
        } else if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
            //时间选择
            cnd.append(" SELECT date_sub(date_format('" + daysTo +"','%y-%m-%d %H'), interval 0 HOUR) as click_date union all ");
            Date date1 = format.parse(daysFrom);
            Date date2 = format.parse(daysTo);
            int bew = DateUtils.hoursBetween(date1, date2);
            for(int i=1; i<bew; i++) {
                cnd.append(" SELECT date_sub(date_format('" + daysTo +"','%y-%m-%d %H'), interval " + i +" HOUR) as click_date union all ");
            }
            cnd.append(" SELECT date_sub(date_format('" + daysTo +"','%y-%m-%d %H'), interval " + bew +" HOUR) as click_date ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd4(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据商品名称查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dp.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据结算状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSettle_state_cnd()))) {
            cnd.append(" and dpr.settle_state = '" + StringUtil.nvl(cnds.getRows().getSettle_state_cnd()) + "' ");
        }
        //根据所属公司查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getCompany_cnd()))) {
            cnd.append(" and dp.company = '" + StringUtil.nvl(cnds.getRows().getCompany_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dpr.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dpr.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +" 23:59:59' ");
        }

        if("".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd())) && "".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))
                && "".equals(StringUtil.nvl(cnds.getRows().getName_cnd())) && "".equals(StringUtil.nvl(cnds.getRows().getSettle_state_cnd()))) {
            cnd.append(" and dpr.visit_time = date_sub(date_format(now(),'%y-%m-%d'), interval 1 day) ");
        }

        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd8(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据商品名称查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dp.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据结算状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSettle_state_cnd()))) {
            cnd.append(" and dpr.settle_state = '" + StringUtil.nvl(cnds.getRows().getSettle_state_cnd()) + "' ");
        }
        //根据所属公司查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getCompany_cnd()))) {
            cnd.append(" and dp.company = '" + StringUtil.nvl(cnds.getRows().getCompany_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dpr.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dpr.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +" 23:59:59' ");
        }

        //根据合作方式查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSettle_way_cnd()))) {
            cnd.append(" and dpr.settle_way = '" + StringUtil.nvl(cnds.getRows().getSettle_way_cnd()) + "' ");
        }

        /*//根据结算人查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_code_cnd()))) {
            cnd.append(" and isu.USER_NAME like '%" + StringUtil.nvl(cnds.getRows().getUser_code_cnd()) + "%' ");
        }*/
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.data.pojo.Cnds apCnd5(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据来源查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSource_cnd()))) {
            cnd.append(" and dvl.source = '" + StringUtil.nvl(cnds.getRows().getSource_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dvl.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +":00:00 '");
            cnds.getRows().setVisit_time_FROM_cnd(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +":00:00");
        }else {
            cnds.getRows().setVisit_time_FROM_cnd("2000-00-00 00:00:00");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dvl.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +":00:00 '");
            cnds.getRows().setVisit_time_TO_cnd(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +":00:00");
        }else {
            cnds.getRows().setVisit_time_TO_cnd("2200-00-00 00:00:00");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }
    public static com.hyy.ifm.data.pojo.Cnds apCnd7(com.hyy.ifm.data.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据来源查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSource_cnd()))) {
            cnd.append(" and dvl.source = '" + StringUtil.nvl(cnds.getRows().getSource_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and dvl.visit_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +":00:00 '");
            cnds.getRows().setVisit_time_FROM_cnd(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +":00:00");
        }else {
            cnds.getRows().setVisit_time_FROM_cnd("2000-00-00 00:00:00");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and dvl.visit_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +":00:00 '");
            cnds.getRows().setVisit_time_TO_cnd(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +":00:00");
        }else {
            cnds.getRows().setVisit_time_TO_cnd("2200-00-00 00:00:00");
        }
        if (!cnds.getUserCode().equals("13")) {
            cnd.append(" and isl.openLgnId = '" + StringUtil.nvl(cnds.getUserCode()) + "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }
    public static com.hyy.ifm.customer.pojo.Cnds apCnd1(com.hyy.ifm.customer.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据手机号模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getMobile_cnd()))) {
            cnd.append(" and du.mobile like '%" + StringUtil.nvl(cnds.getRows().getMobile_cnd()) + "%' ");
        }
        //根据姓名模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_name_cnd()))) {
            cnd.append(" and dui.user_name like '%" + StringUtil.nvl(cnds.getRows().getUser_name_cnd()) + "%' ");
        }
        //根据身份证号码模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getId_card_cnd()))) {
            cnd.append(" and dui.id_card like '%" + StringUtil.nvl(cnds.getRows().getId_card_cnd()) + "%' ");
        }
        //根据银行卡号模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getBank_card_cnd()))) {
            cnd.append(" and dui.bank_card like '%" + StringUtil.nvl(cnds.getRows().getBank_card_cnd()) + "%' ");
        }

        //根据认证状态
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()))) {
            if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("活体")){
                cnds.getRows().setUser_auth_cnd("1");
                cnd.append(" and dui.user_auth like '%" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "%' ");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("个人信息")){
                cnds.getRows().setUser_auth_cnd("2");
                cnd.append(" and dui.user_auth like '%" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "%' ");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("银行卡")){
                cnds.getRows().setUser_auth_cnd("3");
                cnd.append(" and dui.user_auth like '%" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "%' ");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("联系人")){
                cnds.getRows().setUser_auth_cnd("4");
                cnd.append(" and dui.user_auth like '%" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "%' ");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("运营商")){
                cnds.getRows().setUser_auth_cnd("5");
                cnd.append(" and dui.user_auth like '%" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "%' ");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("未认证")){
                cnds.getRows().setUser_name_cnd("");
            }else if(StringUtil.nvl(cnds.getRows().getUser_auth_cnd()).equals("0")){
                cnds.getRows().setUser_name_cnd("");
            }else {
                cnd.append(" and dui.user_auth = '" + StringUtil.nvl(cnds.getRows().getUser_auth_cnd()) + "' ");
            }

        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and du.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据渠道查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getSource_cnd()))) {
            cnd.append(" and du.source = '" + StringUtil.nvl(cnds.getRows().getSource_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" ' ");
        }
        //根据创建时间模糊查询

        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and du.create_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and du.create_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"' ");
        }


        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.config.pojo.Cnds apCnd1(com.hyy.ifm.config.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据名称模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dc.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据链接模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUrl_cnd()))) {
            cnd.append(" and dc.url like '%" + StringUtil.nvl(cnds.getRows().getUrl_cnd()) + "%' ");
        }
        //根据位置查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getPosition_cnd()))) {
            cnd.append(" and dc.position = '" + StringUtil.nvl(cnds.getRows().getPosition_cnd()) + "' ");
        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and dc.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据开始时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getStart_time_FROM_cnd()))) {
            cnd.append(" and dc.start_time >= '"+ StringUtil.nvl(cnds.getRows().getStart_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getStart_time_TO_cnd()))) {
            cnd.append(" and dc.start_time <= '"+ StringUtil.nvl(cnds.getRows().getStart_time_TO_cnd()) +" 23:59:59' ");
        }
        //根据结束时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getEnd_time_FROM_cnd()))) {
            cnd.append(" and dc.end_time >= '"+ StringUtil.nvl(cnds.getRows().getEnd_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getEnd_time_TO_cnd()))) {
            cnd.append(" and dc.end_time <= '"+ StringUtil.nvl(cnds.getRows().getEnd_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.config.pojo.Cnds apCnd2(com.hyy.ifm.config.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据名称模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getItem_value_cnd()))) {
            cnd.append(" and dd.item_value like '%" + StringUtil.nvl(cnds.getRows().getItem_value_cnd()) + "%' ");
        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getIs_use_cnd()))) {
            cnd.append(" and dd.is_use = '" + StringUtil.nvl(cnds.getRows().getIs_use_cnd()) + "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.news.pojo.Cnds apCnd1(com.hyy.ifm.news.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据标题模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getTitle_cnd()))) {
            cnd.append(" and dn.title like '%" + StringUtil.nvl(cnds.getRows().getTitle_cnd()) + "%' ");
        }
        //根据是否置顶查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getPosition_cnd()))) {
            cnd.append(" and dn.position = '" + StringUtil.nvl(cnds.getRows().getPosition_cnd()) + "' ");
        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and dn.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据发布时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getStart_time_FROM_cnd()))) {
            cnd.append(" and dn.start_time >= '"+ StringUtil.nvl(cnds.getRows().getStart_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getStart_time_TO_cnd()))) {
            cnd.append(" and dn.start_time <= '"+ StringUtil.nvl(cnds.getRows().getStart_time_TO_cnd()) +" 23:59:59' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dn.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dn.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.news.pojo.Cnds apCnd2(com.hyy.ifm.news.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据标题模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dny.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据状态查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getStatus_cnd()))) {
            cnd.append(" and dny.status = '" + StringUtil.nvl(cnds.getRows().getStatus_cnd()) + "' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dny.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dny.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.customer.pojo.Cnds apCnd3(com.hyy.ifm.customer.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据手机号精确查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getMobile_cnd()))) {
            cnd.append(" and du.mobile = '" + StringUtil.nvl(cnds.getRows().getMobile_cnd()) + "' ");
        }
        //根据身份证号码精确查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getId_card_cnd()))) {
            cnd.append(" and dui.id_card = '" + StringUtil.nvl(cnds.getRows().getId_card_cnd()) + "' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.customer.pojo.Cnds apCnd4(com.hyy.ifm.customer.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据姓名模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getUser_name_cnd()))) {
            cnds.getRows().setUser_name_cnd(" and su.USER_NAME like '%" + StringUtil.nvl(cnds.getRows().getUser_name_cnd()) + "%' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()))) {
            cnd.append(" and uo.refund_time >= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()))) {
            cnd.append(" and uo.refund_time <= '"+ StringUtil.nvl(cnds.getRows().getVisit_time_TO_cnd()) +"' ");
        }

        if(!"".equals(StringUtil.nvl(cnds.getRows().getRequest_no_cnd()))) {
            cnd.append(" and xo.request_no like '%" + StringUtil.nvl(cnds.getRows().getRequest_no_cnd()) + "%' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getMobile_cnd()))) {
            cnd.append(" and du.mobile like '%" + StringUtil.nvl(cnds.getRows().getMobile_cnd()) + "%' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getId_card_cnd()))) {
            cnd.append(" and dui.id_card like '%" + StringUtil.nvl(cnds.getRows().getId_card_cnd()) + "%' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getBank_card_cnd()))) {
            cnd.append(" and dui.bank_card like '%" + StringUtil.nvl(cnds.getRows().getBank_card_cnd()) + "%' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }

    public static com.hyy.ifm.product.pojo.Cnds apCnd6(com.hyy.ifm.product.pojo.Cnds cnds) {
        StringBuffer cnd = new StringBuffer();
        //根据商品名称模糊查询
        if (!"".equals(StringUtil.nvl(cnds.getRows().getName_cnd()))) {
            cnd.append(" and dp.name like '%" + StringUtil.nvl(cnds.getRows().getName_cnd()) + "%' ");
        }
        //根据创建时间模糊查询
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()))) {
            cnd.append(" and dpvm.create_time >= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_FROM_cnd()) +"' ");
        }
        if(!"".equals(StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()))) {
            cnd.append(" and dpvm.create_time <= '"+ StringUtil.nvl(cnds.getRows().getCreate_time_TO_cnd()) +" 23:59:59' ");
        }
        cnds.setFuzzyCnd(cnd.toString());
        return cnds;
    }
}