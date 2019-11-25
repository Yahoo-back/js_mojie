package com.hyy.ifm.business.dao.impl;

import com.hyy.ifm.business.dao.BusinessCustomerDao;
import com.hyy.ifm.business.pojo.Cnds;
import com.hyy.ifm.common.dao.BaseDao;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 19:25
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "businessCustomerDao")
public class BusinessCustomerDaoImpl extends BaseDao implements BusinessCustomerDao {

    @Override
    public List<Record> qryBusinessCustomerList(Cnds cnds) {
        Sql sql = dao.sqls().create("business.customer.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countBusinessCustomerList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("business.customer.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportBusinessCustomerList(Cnds cnds) {
        Sql sql = dao.sqls().create("business.customer.collection.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

}
