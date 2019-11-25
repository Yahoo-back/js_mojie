package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.CjConfigDao;
import com.hyy.ifm.product.dao.KqConfigDao;
import com.hyy.ifm.product.pojo.CjConfig;
import com.hyy.ifm.product.pojo.KqConfig;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;


@IocBean(name = "cjConfigDao")
public class CjConfigDaoImpl extends BaseDao implements CjConfigDao {


    @Override
    public CjConfig selectByPrimaryKey(Integer id) {
       return dao.fetch(CjConfig.class, Cnd.where("id", "=", id));
    }

}
