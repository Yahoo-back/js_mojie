package com.hyy.ifm.product.dao;



import com.hyy.ifm.product.pojo.KqOrder;
import com.hyy.ifm.product.pojo.LoanOrder;
import org.nutz.dao.entity.Record;

import java.util.List;
import java.util.Map;

public interface LoanOrderDao {


    List<Record> qryLoanOrderByOrderId(Long orderId);

    void insertProductClassify(LoanOrder loanOrder);

}
