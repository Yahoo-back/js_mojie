package com.hyy.ifm.data.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:49
 * @Description Created with IntelliJ IDEA.
 */
public interface DataSourceService {

    /**
     * 点击来源数据统计
     * @param json
     * @return
     */
    public CallBackBean qryDataSourceList(String json);

    /**
     * 点击来源数据统计
     * @param json
     * @return
     */
    public CallBackBean qryQuDaoDataSourceList(String json);

    /**
     * 点击来源数据统计
     * @param json
     * @return
     */
    public CallBackBean qryQuDaoDataSourceSum(String json);


    /**
     * 缩量
     * @param json
     * @return
     */
    public CallBackBean qryQuDaoSl(String json);



    /**
     * 点击来源数据统计
     * @param json
     * @return
     */
    public CallBackBean qryDataSourceSum(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportDataSourceList(String json);

    /**
     * 查询所有来源
     * @param json
     * @return
     */
    public CallBackBean qrySourceAll(String json);
    public CallBackBean qryMySourceAll(String json);
    /**
     * 统计图
     * @param json
     * @return
     */
    public CallBackBean qryDataEchartsSourceBySource(String json);

    /**
     * 导出统计图
     * @param json
     * @return
     */
    public CallBackBean exportDataSourceCharts(String json);

}
