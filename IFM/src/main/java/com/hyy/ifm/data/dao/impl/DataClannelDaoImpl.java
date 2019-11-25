package com.hyy.ifm.data.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.data.dao.DataClannelDao;
import com.hyy.ifm.data.pojo.Cnds;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:45
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataClannelDao")
public class DataClannelDaoImpl extends BaseDao implements DataClannelDao {


    @Override
    public Record qryDataProductBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.list.source");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforEntity(sql);
    }

    @Override
    public Record findWithholdPayBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.source.withhold");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforEntity(sql);
    }

    @Override
    public List<Record> findUserBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.source.user");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> findClickCpaPayBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.source.click");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforlist(sql);
    }

    @Override
    public Record findPayCpaPayBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.source.cpa");
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforEntity(sql);
    }

    @Override
    public List<Record> fetchOpenLogin(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.source");
        sql.setVar("condition1", cnds.getUserCode());
        return this.queryforlist(sql);
    }


}
