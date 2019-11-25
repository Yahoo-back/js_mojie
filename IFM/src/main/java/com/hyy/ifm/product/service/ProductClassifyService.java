package com.hyy.ifm.product.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 10:10
 * @Description Created with IntelliJ IDEA.
 */
public interface ProductClassifyService {

    /**
     * 查询产品类型列表
     * @param json
     * @return
     */
    public CallBackBean qryProductClassifyAll(String json);

    /**
     * 查询产品类型列表
     * @param json
     * @return
     */
    public CallBackBean qryProductClassifyList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportProductClassifyList(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveProductClassify(String json);

    /**
     * 根据id查询
     * @param json
     * @return
     */
    public CallBackBean qryProductClassifyById(String json);

    /**
     * 更新
     * @param json
     * @return
     */
    public CallBackBean updateProductClassify(String json);
}
