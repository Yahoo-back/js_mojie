package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.UserOrderDao;

import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.pojo.UserOrder;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;
import java.util.Map;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "userOrderDao")
public class UserDaoImpl extends BaseDao implements UserOrderDao {

    @Override
    public void updateSelective(UserOrder userOrder) {
        dao.updateIgnoreNull(userOrder);
    }

    @Override
    public UserOrder findByPrimary(Integer id) {
        return dao.fetch(UserOrder.class, Cnd.where("user_id", "=", id));
    }

}
