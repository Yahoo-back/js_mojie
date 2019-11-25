package com.hyy.ifm.data.dao;

import com.hyy.ifm.data.pojo.Cnds;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 11:14
 * @Description Created with IntelliJ IDEA.
 */
public interface DataClannelDao {
    /**
     * 根据渠道查询产品访问
     * @param cnds
     * @return
     */
    Record qryDataProductBySource(Cnds cnds);

    /**
     * 根据渠道已支付金额
     * @param cnds
     * @return
     */
    Record findWithholdPayBySource(Cnds cnds);

    /**
     * 渠道订单数
     * @param cnds
     * @return
     */
    List<Record> findUserBySource(Cnds cnds);

    /**
     * 根据渠道查询点击Cpa数
     * @param
     * @return
     */
    List<Record> findClickCpaPayBySource(Cnds cnds);

    /**
     * 根据渠道查询支付Cpa支付金额
     * @param
     * @return
     */
    Record findPayCpaPayBySource(Cnds cnds);

    List<Record> fetchOpenLogin(Cnds cnds);
}
