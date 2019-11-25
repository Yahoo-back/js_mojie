package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.ProductClassifyDao;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.pojo.DcProductClassify;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 10:13
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
public class ProductClassifyDaoImpl extends BaseDao implements ProductClassifyDao {

    @Override
    public List<DcProductClassify> qryProductClassifyAll() {
        return dao.query(DcProductClassify.class, Cnd.where("status", "=", "0"));
    }

    @Override
    public List<Record> qryProductClassifyList(Cnds cnds) {
        Sql sql = dao.sqls().create("product.classify.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countProductClassifyList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("product.classify.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportProductClassifyList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.product.classify.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public DcProductClassify qryProductClassifyByName(String name, int id, boolean f) {
        Cnd cnd = Cnd.where("name", "=", name).and("status", "<>", "2");
        if(!f) {
            cnd = Cnd.where("name", "=", name).and("id", "<>", id).and("status", "<>", "2");
        }
        return dao.fetch(DcProductClassify.class, cnd);
    }

    @Override
    public void insertProductClassify(DcProductClassify dcProductClassify) {
        dao.insert(dcProductClassify);
    }

    @Override
    public void updateProductClassify(DcProductClassify dcProductClassify) {
        dao.updateIgnoreNull(dcProductClassify);
    }

    @Override
    public DcProductClassify qryProductClassifyById(int id) {
        return dao.fetch(DcProductClassify.class, Cnd.where("id", "=", id));
    }

}
