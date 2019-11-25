package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.KqOrderDao;
import com.hyy.ifm.product.dao.UserOrderDao;
import com.hyy.ifm.product.pojo.KqOrder;
import com.hyy.ifm.product.pojo.UserOrder;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Map;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "kqOrderDao")
public class KqOrderDaoImpl extends BaseDao implements KqOrderDao {



    @Override
    public void insertSelective(KqOrder record) {
        dao.insert(record);
    }

    @Override
    public KqOrder selectLastByUserIdAndTypeAndOuterId(Map<String, Object> param ) {

        Cnd cnd = Cnd.where("type", "=", param.get("type")).and("userId", "=", param.get("userId")).and("outerId","=",param.get("outerId"));
        return  dao.fetch(KqOrder.class,cnd);
    }


}
