package com.hyy.ifm.customer.service;

import com.hyy.ifm.common.pojo.CallBackBean;


public interface RefundService {

    /**
     * 退款客户
     * @param json
     * @return
     */
    public CallBackBean qryRefund(String json);

    /**
     * 跟踪
     * @param json
     * @return
     */
    public CallBackBean updateOrderAuditStatus(String json);

    /**
     * 我的退款跟踪
     * @param json
     * @return
     */
    public CallBackBean qryMyRefund(String json);

    /**
     * 保存退款操作
     * @param json
     * @return
     */
    public CallBackBean saveRefund(String json);

    /**
     * 根据id查询客户
     * @param json
     * @return
     */
    public CallBackBean qryRefundAttachById(String json);

    /**
     * 客户退款列表
     * @param json
     * @return
     */
    public CallBackBean qryRefundList(String json);



    /**
     * 退款统计列表
     * @param json
     * @return
     */
    public CallBackBean listStatistics(String json);

    /**
     * 邮件退款列表
     * @param json
     * @return
     */
    public CallBackBean mailRefundList(String json);



}
