package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.XfCallbackLogDao;
import com.hyy.ifm.product.pojo.XfCallbackLog;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(name = "xfCallbackLogDao")
public class XfCallbackLogDaoImpl extends BaseDao implements XfCallbackLogDao {


    @Override
    public int countTable(String tableName) {
        try {
            Sql sql = dao.sqls().create("shardTable.countTable");
            sql.setParam("tableName", tableName);
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void createTable(String tableName) {
        Sql sql = dao.sqls().create("shardTable.createTable.xfCallbackLog");
        sql.setVar("tableName", tableName);
        dao.execute(sql);
    }

    @Override
    public void saveShard(String tableName, XfCallbackLog record) {
        Sql sql = dao.sqls().create("shardTable.saveShard.xfCallbackLog");
        sql.setVar("tableName", tableName);
        sql.setParam("userId", record.getUserId());
        sql.setParam("type", record.getType());
        sql.setParam("createTime", record.getCreateTime());
        sql.setParam("content", record.getContent());
        dao.execute(sql);
    }


}
