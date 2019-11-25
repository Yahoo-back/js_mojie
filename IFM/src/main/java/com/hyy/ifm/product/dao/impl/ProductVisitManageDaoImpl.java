package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.ProductVisitManageDao;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProductVisitManage;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;


@IocBean(name = "productVisitManageDao")
public class ProductVisitManageDaoImpl extends BaseDao implements ProductVisitManageDao {

    @Override
    public List<Record> qryProductVisitManageList(Cnds cnds) {
        Sql sql = dao.sqls().create("product.visitManage.list");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public void insertProductVisitManage(DcProductVisitManage dcProductVisitManage) {
        dao.insert(dcProductVisitManage);
    }

    @Override
    public void updateProductVisitManage(DcProductVisitManage dcProductVisitManage) {
        dao.updateIgnoreNull(dcProductVisitManage);
    }

    @Override
    public DcProductVisitManage qryProductVisitManageById(int id) {
        return dao.fetch(DcProductVisitManage.class, Cnd.where("id", "=", id));
    }

    @Override
    public List<Record> exportProductList(Cnds cnds) {
        Sql sql = dao.sqls().create("product.collection.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public Record qryProductVisitCount(int id) {
        Sql sql = dao.sqls().create("product.visitCount.data");
        sql.params().set("productId", id);
        return this.queryforEntity(sql);
    }

    @Override
    public List<Record> qryVisitProductListAll() {
        Sql sql = dao.sqls().create("product.visitManageProduct.list");
        return this.queryforlist(sql);
    }
}
