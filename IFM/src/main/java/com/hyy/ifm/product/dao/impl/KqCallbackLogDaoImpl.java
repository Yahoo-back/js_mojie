package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;

import com.hyy.ifm.product.dao.KqCallbackLogDao;
import com.hyy.ifm.product.pojo.KqCallbackLog;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "kqCallbackLogDao")
public class KqCallbackLogDaoImpl extends BaseDao implements KqCallbackLogDao {


    @Override
    public void insertSelective(KqCallbackLog record) {
        dao.insert(record);
    }


}
