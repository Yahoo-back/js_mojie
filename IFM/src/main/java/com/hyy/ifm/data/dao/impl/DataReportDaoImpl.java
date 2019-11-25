package com.hyy.ifm.data.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.data.dao.DataReportDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcProductReport;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 15:25
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataReportDao")
public class DataReportDaoImpl extends BaseDao implements DataReportDao {

    @Override
    public List<Record> qryDataReportList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.report.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataFinanceReportList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.finance.report.list.data");
        sql.params().set("pageNum",getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition",cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countDataReportList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.report.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DcProductReport fetchProductReportById(int reportId) {
        return dao.fetch(DcProductReport.class, Cnd.where("id", "=", reportId));
    }

    @Override
    public void updateProductReport(DcProductReport dcProductReport) {
        dao.updateIgnoreNull(dcProductReport);
    }

    @Override
    public List<Record> exportDataProductReportList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.collection.product.report.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> exportDataFinanceReportList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.collection.finance.report.list.data");
        sql.setVar("condition",cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> queryDataProductReportTotal(Cnds cnds) {
        Sql sql = dao.sqls().create("data.collection.product.report.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public void insetProductReport(DcProductReport dcProductReport) {
        dao.insert(dcProductReport);
    }

    @Override
    public Record countDayOfVisitCountByProduct(int id) {
        Sql sql = dao.sqls().create("data.day.of.visit.list.data");
        sql.params().set("productId", id);
        return this.queryforEntity(sql);
    }

    @Override
    public DcProductReport qryCountProductInfo(String id) {
        return dao.fetch(DcProductReport.class, Cnd.where("id", "=", id));
    }

    @Override
    public List<Record> qryProductSettleWayAll(String json) {
        Sql sql = dao.sqls().create("data.report.on.list.data");
        return this.queryforlist(sql);
    }
}
