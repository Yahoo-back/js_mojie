package com.hyy.ifm.data.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:49
 * @Description Created with IntelliJ IDEA.
 */
public interface DataChannelService {

    /**
     * 渠道统计
     * @param
     * @return
     */
    CallBackBean qryDataFinanceReportList(String json);


    /**
     * 我的渠道统计
     * @param
     * @return
     */
    CallBackBean qryMyChannelList(String json);





}
