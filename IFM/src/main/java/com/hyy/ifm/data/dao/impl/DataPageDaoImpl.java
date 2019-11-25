package com.hyy.ifm.data.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.data.dao.DataPageDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcVisitLog;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 11:16
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataPageDao")
public class DataPageDaoImpl extends BaseDao implements DataPageDao {

    @Override
    public List<Record> qryDataProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.page.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countDataProductList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.page.count.data");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportDataPageList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.collection.page.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataPageCharts(Cnds cnds, String classify) {
        Sql sql = dao.sqls().create("data.echarts.page.list.data");
        sql.setVar("condition1", cnds.getFuzzyCnd());
        sql.setVar("condition2", classify);
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryPageListAll() {
        Sql sql = dao.sqls().create("data.page.classify.list.data");
        return this.queryforlist(sql);
    }

}
