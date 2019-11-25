package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProduct;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:27
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "productDao")
public class ProductDaoImpl extends BaseDao implements ProductDao {

    @Override
    public List<Record> qryProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("product.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countProductList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("product.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DcProduct qryProductByName(String name, int id, boolean f) {
        Cnd cnd = Cnd.where("name", "=", name).and("status", "<>", "2");
        if(!f) {
            cnd = Cnd.where("name", "=", name).and("id", "<>", id).and("status", "<>", "2");
        }
        return dao.fetch(DcProduct.class, cnd);
    }

    @Override
    public void insertProduct(DcProduct dcProduct) {
        dao.insert(dcProduct);
    }

    @Override
    public void updateProduct(DcProduct dcProduct) {
        dao.updateIgnoreNull(dcProduct);
    }

    @Override
    public DcProduct qryProductById(int id) {
        return dao.fetch(DcProduct.class, Cnd.where("id", "=", id));
    }

    @Override
    public int countProductListAll() {
        return dao.count(DcProduct.class);
    }

    @Override
    public List<Record> exportProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("product.collection.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public List<DcProduct> qryProductListAll() {
        return dao.query(DcProduct.class, Cnd.where("status", "<>", "2"));
    }

    @Override
    public List<DcProduct> qryProductListWithOutDelete() {
        return dao.query(DcProduct.class, Cnd.where("status", "<>", "2"));
    }

    @Override
    public List<Record> qryProductCompanyAll() {
        Sql sql = dao.sqls().create("product.company.on.list.data");
        return this.queryforlist(sql);
    }

}
