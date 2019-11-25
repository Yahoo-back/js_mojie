package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.CjOrderDao;
import com.hyy.ifm.product.dao.KqOrderDao;
import com.hyy.ifm.product.pojo.CjOrder;
import com.hyy.ifm.product.pojo.KqOrder;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Map;


@IocBean(name = "cjOrderDao")
public class CjOrderDaoImpl extends BaseDao implements CjOrderDao {



    @Override
    public void insertSelective(CjOrder record) {
        dao.insert(record);
    }

    @Override
    public CjOrder selectLastByUserIdAndTypeAndOuterId(Map<String, Object> param ) {

        Cnd cnd = Cnd.where("type", "=", param.get("type")).and("userId", "=", param.get("userId")).and("outerId","=",param.get("outerId"));
        return  dao.fetch(CjOrder.class,cnd);
    }


}
