package com.hyy.ifm.customer.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.customer.dao.RefundDao;
import com.hyy.ifm.customer.pojo.*;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


@IocBean(name = "refundDao")
public class RefundDaoImpl extends BaseDao implements RefundDao {

    @Override
    public List<Record> qryRefund(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.refund.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }



    @Override
        public void updateOrderAuditStatus(UserOrder userOrder) {
        dao.update(userOrder);
    }

    @Override
    public List<Record> qryMyRefund(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.myRefund.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.params().set("sysUserId", cnds.getUserCode());
        return this.queryforlist(sql);
    }

    @Override
    public int qryMyRefundCount(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.myRefund.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insertUserRefund(DcUserRefund dcUserRefund) {
        dao.insert(dcUserRefund);
    }

    @Override
    public List<DcUserRefund> qryRefundAttachById(int id) {
        return dao.query(DcUserRefund.class, Cnd.where("user_id", "=", id));
    }

    @Override
    public List<Record> qryRefundList(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.refundList.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int qryRefundListCount(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.refundList.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> listStatistics(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.statisticsList.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("name", cnds.getRows().getUser_name_cnd());
        sql.setVar("time", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int listStatisticsCount(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.statisticsList.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> mailRefundList(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.list.data.mail.return");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int mailRefundListCount(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.list.data.mail.return.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
