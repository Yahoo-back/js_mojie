package com.hyy.ifm.business.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 19:21
 * @Description Created with IntelliJ IDEA.
 */
public interface BusinessCustomerService {

    /**
     * 商务合作列表
     * @param json
     * @return
     */
    public CallBackBean qryBusinessCustomerList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportBusinessCustomerList(String json);

}
