package com.hyy.ifm.customer.service;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.customer.pojo.Qdao;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:30
 * @Description Created with IntelliJ IDEA.
 */
public interface CustomerService {

    /**
     * 客户列表
     * @param json
     * @return
     */
    public CallBackBean qryCustomerList(String json);

    /**
     * 客户扣费汇总
     * @param json
     * @return
     */
    public CallBackBean qryCustomerMoneyAll(String json);

    /**
     * 客户扣费汇总
     * @param json
     * @return
     */
    public CallBackBean qryCustomerMoneyAllSys(String json);

    /**
     * 导出客户列表
     * @param json
     * @return
     */
    public CallBackBean exportCustomerList(String json);




    /**
     * 根据id查询客户
     * @param json
     * @return
     */
    public CallBackBean qryCustomerById(String json);


    /**
     * 根据id修改客户
     * @param json
     * @return
     */
    public CallBackBean updateCustomerById(String json);
    /**
     * 更新用户
     * @param json
     * @return
     */
    public CallBackBean updateCustomer(String json);

}
