package com.hyy.ifm.customer.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.customer.dao.CustomerDao;
import com.hyy.ifm.customer.pojo.*;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.sys.pojo.IfmLogin;
import com.hyy.ifm.sys.pojo.IfmRole;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:38
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "customerDao")
public class CustomerDaoImpl extends BaseDao implements CustomerDao {

    @Override
    public List<Record> qryCustomerList(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }
    @Override
    public List<Record> qryCustomerListNone(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.list.data.none");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryCustomerMoneyAll(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.list.data.money");
       // sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public Record qrySysNameAllMoney(String source) {
        Sql sql = dao.sqls().create("customer.list.data.money.source");
        sql.params().set("source", source);
        return this.queryforEntity(sql);
    }



    @Override
    public int countCustomerList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportCustomerList(Cnds cnds) {
        Sql sql = dao.sqls().create("customer.collection.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public Record qryCustomerById(int id) {
        Sql sql = dao.sqls().create("customer.list.data.by.id");
        sql.params().set("customerId", id);
        return this.queryforEntity(sql);
    }

    @Override
    public List<DcUser> qryCustomerByUuid(String uuid) {
        return dao.query(DcUser.class, Cnd.where("id", "=", uuid));
    }

    @Override
        public List<Qdao> qryQdaoBySource(String source,String visitTime) {
        return dao.query(Qdao.class, Cnd.where("source", "=", source).and("update_time","=",visitTime));
    }

    @Override
    public List<DcUserInfo> qryCustomerInfoById(int id) {
        return dao.query(DcUserInfo.class, Cnd.where("user_id", "=", id));
    }

    @Override
    public List<DcUser> qryUserById(int id) {
        return dao.query(DcUser.class, Cnd.where("id", "=", id));
    }

    @Override
    public List<UserOrder> qryUserOrderByStatus() {
//      Sql sql = dao.sqls().create("customer.list.data.by.status");
        return dao.query(UserOrder.class, Cnd.where("status", "=", 0 ).desc("apply_time"));

    }


    @Override
    public List<DcUserAttach> qryCustomerAttachById(int id) {
        return dao.query(DcUserAttach.class, Cnd.where("user_id", "=", id));
    }

    @Override
    public DcUser qryCustomerById2(int id) {
        return dao.fetch(DcUser.class, Cnd.where("id", "=", id));
    }

    @Override
    public void updateCustomer(DcUser dcUser) {
        dao.updateIgnoreNull(dcUser);
    }

    @Override
    public void updateCustomerInfo(DcUserInfo dcUserInfo) {
        dao.updateIgnoreNull(dcUserInfo);
    }

    @Override
    public int countCustomerListNone(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("customer.list.data.none.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> qryPayAmt(Cnds cnds, String s) {
        Sql sql = dao.sqls().create("customer.collection.pay.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", s);
        return this.queryforlist(sql);
    }

}
