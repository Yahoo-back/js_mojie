package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.dispcter.inlet.Inlet;
import com.hyy.ifm.product.service.KqService;
import com.hyy.ifm.product.service.ProductClassifyService;
import com.hyy.ifm.product.service.ProductService;
import com.hyy.ifm.product.service.ProductVisitManageService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:15
 * @Description 商品管理
 */
@IocBean(scope="singleton", singleton=true)
public class ProductInlet extends Inlet {

    private static final long serialVersionUID = 3630544795640603158L;

    @Inject
    private ProductService productService;
    @Inject
    private ProductClassifyService productClassifyService;

    @Inject
    private ProductVisitManageService productVisitManageService;

    @Inject
    private KqService kqService;

    @Override
    public void init() {
        /**
         * 商品列表
         */
        serviceTreeMap.put("qryProductList", productService);

        /**
         * 保存商品
         */
        serviceTreeMap.put("saveProduct", productService);

        /**
         * 根据id查询商品
         */
        serviceTreeMap.put("qryProductById", productService);

        /**
         * 上下架、删除商品
         */
        serviceTreeMap.put("updateProduct", productService);

        /**
         * 置顶商品
         */
        serviceTreeMap.put("topPosition", productService);

        /**
         * 置尾商品
         */
        serviceTreeMap.put("basePosition", productService);

        /**
         * 取消置顶或置尾商品
         */
        serviceTreeMap.put("cancelPosition", productService);

        /**
         * 导出商品
         */
        serviceTreeMap.put("exportProductList", productService);

        /**
         * 查询所有商品列表不包括删除
         */
        serviceTreeMap.put("qryProductListAll", productService);



        /**
         * 查询产品类型列表
         */
        serviceTreeMap.put("qryProductClassifyAll", productClassifyService);

        /**
         * 查询产品类型列表
         */
        serviceTreeMap.put("qryProductClassifyList", productClassifyService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportProductClassifyList", productClassifyService);

        /**
         * 保存
         */
        serviceTreeMap.put("saveProductClassify", productClassifyService);

        /**
         * 根据id查询
         */
        serviceTreeMap.put("qryProductClassifyById", productClassifyService);

        /**
         * 更新
         */
        serviceTreeMap.put("updateProductClassify", productClassifyService);

        /**
         * 查询产品公司名称
         */
        serviceTreeMap.put("qryProductCompanyAll", productService);

        serviceTreeMap.put("kqBindCardPay", kqService);

        serviceTreeMap.put("thirdPay", kqService);

        /**
         * 商品访问管理列表
         */
        serviceTreeMap.put("qryProductVisitManageList", productVisitManageService);

        /**
         * 保存商品
         */
        serviceTreeMap.put("saveProductVisitManage", productVisitManageService);

        /**
         * 根据id查询商品
         */
        serviceTreeMap.put("qryProductVisitManageById", productVisitManageService);

        /**
         * 查询商品当天访问数
         */
        serviceTreeMap.put("qryProductVisitCount", productVisitManageService);

        /**
         * 修改商品访问管理状态
         */
        serviceTreeMap.put("updateProductVisitManage", productVisitManageService);

        /**
         * 修改商品访问管理状态
         */
        serviceTreeMap.put("qryVisitProductListAll", productVisitManageService);

    }

}
