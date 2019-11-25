package com.hyy.ifm.product.service;

import com.hyy.ifm.common.pojo.CallBackBean;

public interface ProductVisitManageService {

    /**
     * 商品访问管理列表
     * @param json
     * @return
     */
    public CallBackBean qryProductVisitManageList(String json);

    /**
     * 保存商品
     * @param json
     * @return
     */
    public CallBackBean saveProductVisitManage(String json);

    /**
     * 根据id查询商品
     * @param json
     * @return
     */
    public CallBackBean qryProductVisitManageById(String json);

    /**
     * 导出商品
     * @param json
     * @return
     */
    public CallBackBean exportProductList(String json);

    /**
     * 查询商品当天访问数
     * @param json
     * @return
     */
    public CallBackBean qryProductVisitCount(String json);

    /**
     * 修改商品访问状态
     * @param json
     * @return
     */
    public CallBackBean updateProductVisitManage(String json);

    /**
     * 查询所有商品列表不包括删除和已管理的
     * @param json
     * @return
     */
    public CallBackBean qryVisitProductListAll(String json);


}
