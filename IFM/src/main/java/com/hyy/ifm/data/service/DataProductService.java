package com.hyy.ifm.data.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:43
 * @Description Created with IntelliJ IDEA.
 */
public interface DataProductService {

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean qryDataProductList(String json);

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean qryDataProductListBy1(String json);

    /**
     * 产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean qryDataProductListBy2(String json);


    /**
     * 导出产品访问统计表
     * @param json
     * @return
     */
    public CallBackBean exportDataProductList(String json);

    /**
     * 根据id查询产品列表数据
     * @param json
     * @return
     */
    public CallBackBean qryDataEchartsProductById(String json);

    /**
     * 导出产品访问统计图
     * @param json
     * @return
     */
    public CallBackBean exportDataProductCharts(String json);

}
