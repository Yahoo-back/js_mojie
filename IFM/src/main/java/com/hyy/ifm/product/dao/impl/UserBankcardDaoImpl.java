package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;

import com.hyy.ifm.product.dao.KqCallbackLogDao;
import com.hyy.ifm.product.dao.UserBankcardDao;
import com.hyy.ifm.product.pojo.UserBankcard;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;
import java.util.Map;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "userBankcardDao")
public class UserBankcardDaoImpl extends BaseDao implements UserBankcardDao {


    @Override
    public UserBankcard selectByUserAndCardAndAuthFrom(Map<String, Object> param) {
        Cnd cnd = Cnd.where("userId", "=", param.get("userId")).and("cardNo", "=", param.get("cardNo")).and("authFrom","=",param.get("authFrom"));
        return  dao.fetch(UserBankcard.class,cnd);
    }


    @Override
    public UserBankcard selectByUserId(Map<String, Object> param) {
        Cnd cnd = Cnd.where("userId", "=", param.get("userId"));
        return  dao.fetch(UserBankcard.class,cnd);
    }


}
