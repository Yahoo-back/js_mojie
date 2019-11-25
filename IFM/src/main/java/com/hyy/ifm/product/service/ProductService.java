package com.hyy.ifm.product.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:19
 * @Description Created with IntelliJ IDEA.
 */
public interface ProductService {

    /**
     * 商品列表
     * @param json
     * @return
     */
    public CallBackBean qryProductList(String json);

    /**
     * 保存商品
     * @param json
     * @return
     */
    public CallBackBean saveProduct(String json);

    /**
     * 根据id查询商品
     * @param json
     * @return
     */
    public CallBackBean qryProductById(String json);

    /**
     * 上下架删除商品
     * @param json
     * @return
     */
    public CallBackBean updateProduct(String json);

    /**
     * 置顶商品
     * @param json
     * @return
     */
    public CallBackBean topPosition(String json);

    /**
     * 置尾商品
     * @param json
     * @return
     */
    public CallBackBean basePosition(String json);

    /**
     * 取消置顶或置尾商品
     * @param json
     * @return
     */
    public CallBackBean cancelPosition(String json);

    /**
     * 导出商品
     * @param json
     * @return
     */
    public CallBackBean exportProductList(String json);

    /**
     * 查询所有商品列表不包括删除
     * @param json
     * @return
     */
    public CallBackBean qryProductListAll(String json);

    /**
     * 查询产品公司名称
     * @param json
     * @return
     */
    public CallBackBean qryProductCompanyAll(String json);

}
