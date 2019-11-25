package com.hyy.ifm.data.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.data.dao.DataProductDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:45
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataProductDao")
public class DataProductDaoImpl extends BaseDao implements DataProductDao {

    @Override
    public Record qryDataProductBySource(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.list.source");
        sql.setVar("condition", cnds.getFuzzyCnd());
        sql.setVar("condition1", cnds.getRows().getSource());
        return this.queryforEntity(sql);
    }

    @Override
    public List<Record> qryDataProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.product.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataSourceListBy2(Cnds cnds) {//下架
        Sql sql = dao.sqls().create("data.product.list.dataBy2");
        sql.params().set("pageNum", ((StringUtil.parseInt(cnds.getPageNum()) - 1) * 4));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countBy2(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.list.dataBy2.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countBy1(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.list.dataBy1.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> qryDataSourceListBy1(Cnds cnds) {//上架
        Sql sql = dao.sqls().create("data.product.list.dataBy1");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }
    @Override
    public int countDataSourceListBy2(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.count.dataBy2");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countDataSourceListBy1(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.count.dataBy1");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countDataProductList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("data.product.count.data");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportDataProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("data.collection.product.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<Record> qryDataEchartsProductById(Cnds cnds, String productId) {
        Sql sql = dao.sqls().create("data.echarts.product.list.data");
        sql.setVar("condition1", cnds.getFuzzyCnd());
        sql.setVar("condition2", productId);
        return this.queryforlist(sql);
    }

}
