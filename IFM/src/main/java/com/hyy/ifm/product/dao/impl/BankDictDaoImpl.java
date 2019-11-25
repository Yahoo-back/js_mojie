package com.hyy.ifm.product.dao.impl;




import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.BankDictDao;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.Map;
@IocBean(name = "bankDictDao")
public class BankDictDaoImpl extends BaseDao implements BankDictDao {




    @Override
    public Record selectByItemValue(String bankOpen) {
        Sql sql = dao.sqls().create("product.bank.data");
        sql.setVar("condition", bankOpen);
        return this.queryforEntity(sql);
    }
}
