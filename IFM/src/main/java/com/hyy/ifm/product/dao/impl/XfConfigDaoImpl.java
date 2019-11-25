package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.CjConfigDao;
import com.hyy.ifm.product.dao.XfConfigDao;
import com.hyy.ifm.product.pojo.CjConfig;
import com.hyy.ifm.product.pojo.XfConfig;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;


@IocBean(name = "xfConfigDao")
public class XfConfigDaoImpl extends BaseDao implements XfConfigDao {


    @Override
    public XfConfig selectByPrimaryKey(Integer id) {
       return dao.fetch(XfConfig.class, Cnd.where("id", "=", id));
    }

}
