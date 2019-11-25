package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;

import com.hyy.ifm.product.dao.KqConfigDao;
import com.hyy.ifm.product.dao.KqOrderDao;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.pojo.KqConfig;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Map;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "kqConfigDao")
public class KqConfigDaoImpl extends BaseDao implements KqConfigDao {


    @Override
    public KqConfig selectByPrimaryKey(Integer id) {
       return dao.fetch(KqConfig.class, Cnd.where("id", "=", id));
    }

}
