package com.hyy.ifm.data.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 11:12
 * @Description Created with IntelliJ IDEA.
 */
public interface DataPageService {

    /**
     * 页面访问数据统计
     * @param json
     * @return
     */
    public CallBackBean qryPageListAll(String json);

    /**
     * 页面访问数据统计
     * @param json
     * @return
     */
    public CallBackBean qryDataPageList(String json);

    /**
     * 导出页面访问数据统计
     * @param json
     * @return
     */
    public CallBackBean exportDataPageList(String json);

    /**
     * 页面访问统计图
     * @param json
     * @return
     */
    public CallBackBean qryDataPageCharts(String json);

    /**
     * 导出页面访问统计
     * @param json
     * @return
     */
    public CallBackBean exportDataPageCharts(String json);

}
