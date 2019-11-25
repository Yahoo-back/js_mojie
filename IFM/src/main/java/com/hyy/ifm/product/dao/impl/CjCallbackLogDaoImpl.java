package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.CjCallbackLogDao;
import com.hyy.ifm.product.dao.KqCallbackLogDao;
import com.hyy.ifm.product.pojo.CjCallbackLog;
import com.hyy.ifm.product.pojo.KqCallbackLog;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(name = "cjCallbackLogDao")
public class CjCallbackLogDaoImpl extends BaseDao implements CjCallbackLogDao {


    @Override
    public void insertSelective(CjCallbackLog record) {
        dao.insert(record);
    }


}
