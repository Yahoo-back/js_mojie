package com.hyy.ifm.data.dao.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.customer.pojo.QdaoSl;
import com.hyy.ifm.data.dao.DataSourceDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcVisitLog;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:53
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataSourceDao")
public class DataSourceDaoImpl extends BaseDao implements DataSourceDao {

    @Override
    public List<Record> qryDataSourceList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.source.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataSourceListNoPage(Cnds cnds) {
        Sql sql = dao.sqls().create("data.source.list.data.NoPage");
       // sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }



    @Override
    public List<Record> qryDataSourceListBySource( Cnds cnds,String source) {
        Sql sql = dao.sqls().create("data.source.list.data.source");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setParam("source", source);
        sql.setVar("condition", cnds.getFuzzyCnd());
       // sql.setParam("visit_time", visitTime);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryQuDaoSl(Cnds cnds, String source) {
        Sql sql = dao.sqls().create("data.source.list.sl");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setParam("source", source);
        //sql.setVar("condition", cnds.getFuzzyCnd());
        // sql.setParam("visit_time", visitTime);
        return this.queryforlist(sql);
    }
    @Override
    public List<Record> qryQuDaoSl(Cnds cnds) {
        Sql sql = dao.sqls().create("data.source.list1.sl");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        //sql.setVar("condition", cnds.getFuzzyCnd());
        // sql.setParam("visit_time", visitTime);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryQuDaoSlAllSource(Cnds cnds, String source) {
        Sql sql = dao.sqls().create("data.source.list.sl.all");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        //sql.setParam("source", source);
        sql.setVar("condition", cnds.getFuzzyCnd());
        // sql.setParam("visit_time", visitTime);
        return this.queryforlist(sql);
    }

    @Override
    public int counQuDaoSlAllSource(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.source.list.sl.all.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> qryDataSourceListBySourceNoPage( Cnds cnds,String source) {
        Sql sql = dao.sqls().create("data.source.list.data.source.NoPage");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setParam("source", source);
        sql.setVar("condition", cnds.getFuzzyCnd());
        // sql.setParam("visit_time", visitTime);
        return this.queryforlist(sql);
    }



    @Override
    public List<Record> qryDataSourceUserAuth(String source,int userAuth,String createTime) {
        Sql sql = dao.sqls().create("data.source.count.data.userAuth");
        sql.setParam("source", source);
        sql.setParam("userAuth", userAuth);
        sql.setParam("create_time", createTime);
        return this.queryforlist(sql);

    }

    @Override
    public List<Record> qryDataSourceSumMoney(String source,String createTime) {
        Sql sql = dao.sqls().create("data.source.count.data.sumMoney");
        sql.setParam("source", source);
        sql.setParam("create_time", createTime);
        return this.queryforlist(sql);

    }

    @Override
    public List<Record> qryDataSourceListMobile(String source) {
        Sql sql = dao.sqls().create("data.source.list.data.mobile");
        //sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("source",source );
        return this.queryforlist(sql);
    }


    @Override
    public List<Record> qryDataSourceSum(Cnds cnds) {
        Sql sql = dao.sqls().create("data.source.list.data.sum");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countDataSourceList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.source.count.data");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public int countDataSourceListQd(Cnds cnds,String source) {
        try {
            Sql sql = dao.sqls().create("data.source.list.data.source.page");
            sql.setVar("condition", cnds.getFuzzyCnd());
            sql.setParam("source", source);
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportDataSourceList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.data.source.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qrySourceAll() {
        Sql sql = dao.sqls().create("data.source.classify.list.data");
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryMySource(String userCode) {
        Sql sql = dao.sqls().create("data.product.source");
        sql.setVar("condition1", userCode.equals("13")?"":userCode);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataEchartsSourceBySource(Cnds cnds, String s) {
        Sql sql = dao.sqls().create("data.echarts.source.list.data");
        sql.setVar("condition1", cnds.getFuzzyCnd());
        sql.setVar("condition2", s);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryRegisterAndUvCount(Cnds cnds) {
        Sql sql = dao.sqls().create("data.source.register.list.count");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataRoorList(Cnds cnds , String s ,String h) {
        Sql sql = dao.sqls().create("data.source.roor.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition2", s);
        sql.setVar("condition3", h);
        return this.queryforlist(sql);
    }

    public List<Record> qryDataRoorList(Cnds cnds,String h ) {
        Sql sql = dao.sqls().create("data.source.roor.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition3", h);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataSum(String userCode, String startTime, String endTime) {
        Sql sql = dao.sqls().create("data.source.data.Sum.data");
        sql.setVar("condition",userCode);
        sql.setVar("condition1", startTime);
        sql.setVar("condition2", endTime);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataSum( String startTime, String endTime) {
        Sql sql = dao.sqls().create("data.source.data.Sum1.data");
        sql.setVar("condition1", startTime);
        sql.setVar("condition2", endTime);
        return this.queryforlist(sql);
    }
    @Override
    public List<Record> qryDataSumList(Cnds cnds,String userCode, String startTime, String endTime) {
        Sql sql = dao.sqls().create("data.source.data.Sum.list");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition",userCode);
        sql.setVar("condition1", startTime);
        sql.setVar("condition2", endTime);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataSumList(Cnds cnds, String startTime, String endTime) {
        Sql sql = dao.sqls().create("data.source.data.Sum1.list");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition1", startTime);
        sql.setVar("condition2", endTime);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryQuDaoLoan(Cnds cnds,String userCode,String s ,String h) {
        Sql sql = dao.sqls().create("data.source.roor.list.loan");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition",  userCode);
        sql.setVar("condition2", s);
        sql.setVar("condition3", h);
        return this.queryforlist(sql);
    }

}
