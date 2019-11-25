package com.hyy.ifm.data.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 14:25
 * @Description Created with IntelliJ IDEA.
 */
public interface DataReportService {

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean qryDataReportList(String json);

    /**
     * 财务统计报表
     * @param json
     * @return
     */
    public CallBackBean qryDataFinanceReportList(String json);

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean countProductInfo(String json);

    /**
     * 结算统计表
     * @param json
     * @return
     */
    public CallBackBean settleProductInfo(String json);

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean settleProduct(String json);

    /**
     * 导出数据报表
     * @param json
     * @return
     */
    public CallBackBean exportDataProductReportList(String json);

    /**
     * 导出财务报表
     * @param json
     * @return
     */
    public CallBackBean exportDataFinanceReportList(String json);

    /**
     * 统计数据
     * @param json
     * @return
     */
    public CallBackBean queryDataProductReportTotal(String json);

    /**
     * 查询统计数据
     * @param json
     * @return
     */
    public CallBackBean qryCountProductInfo(String json);

    /**
     * 结算统计数据
     * @param json
     * @return
     */
    public CallBackBean qrySettleProductInfo(String json);

    /**
     * 查询所有的合作方式
     * @param json
     * @return
     */

    public CallBackBean qryProductSettleWayAll(String json);

}
