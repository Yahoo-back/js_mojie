package com.hyy.ifm.product.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.product.dao.LoanOrderDao;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.pojo.LoanOrder;
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
@IocBean(name = "loanOrderDao")
public class LoanDaoImpl extends BaseDao implements LoanOrderDao {


    @Override
    public List<Record> qryLoanOrderByOrderId(Long orderId) {
        Sql sql = dao.sqls().create("loan.order.byOrderId");
        sql.params().set("orderId", orderId );
        return this.queryforlist(sql);

    }

    @Override
    public void insertProductClassify(LoanOrder loanOrder) {
        dao.insert(loanOrder);
    }


}
