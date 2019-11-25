package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.XfOrderDao;
import com.hyy.ifm.product.pojo.XfOrder;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;
import java.util.Map;


@IocBean(name = "xfOrderDao")
public class XfOrderDaoImpl extends BaseDao implements XfOrderDao {

    @Override
    public void insertSelective(XfOrder record) {
        dao.insert(record);
    }

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
        Sql sql = dao.sqls().create("shardTable.createTable.xfOrder");
        sql.setVar("tableName", tableName);
        dao.execute(sql);
    }

    @Override
    public void saveShard(String tableName, XfOrder record) {
        Sql sql = dao.sqls().create("shardTable.saveShard.xfOrder");
        sql.setVar("tableName", tableName);
        sql.setParam("userId", record.getUserId());
        sql.setParam("type", record.getType());
        sql.setParam("outerId", record.getOuterId());
        sql.setParam("requestNo", record.getRequestNo());
        sql.setParam("userIp", record.getUserIp());
        sql.setParam("amt", record.getAmt());
        sql.setParam("protocolNo", record.getOrderTrxid());
        sql.setParam("createTime", record.getCreateTime());
        sql.setParam("status", record.getStatus());
        sql.setParam("payDate", record.getPayDate());
        sql.setParam("failReason", record.getFailReason());
        dao.execute(sql);
    }

    @Override
    public XfOrder selectLastByUserIdAndTypeAndOuterId(String tableName, Map<String, Object> param ) {
        Sql sql = dao.sqls().create("shardTable.listShardSelective.xfOrder");
        sql.setVar("tableName", tableName);
        sql.setParam("type", param.get("type"));
        sql.setParam("userId", param.get("userId"));
        sql.setParam("outerId", param.get("outerId"));
        dao.execute(sql);
        List<XfOrder> rs = sql.getList(XfOrder.class);
        XfOrder re = null == rs || rs.size() == 0 ? new XfOrder() : rs.get(0);
        return re;
    }


}
