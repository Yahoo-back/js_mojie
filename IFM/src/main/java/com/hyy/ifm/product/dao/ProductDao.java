package com.hyy.ifm.product.dao;

import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProduct;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:21
 * @Description Created with IntelliJ IDEA.
 */
public interface ProductDao {

    /**
     * 商品列表
     * @param cnds
     * @return
     */
    List<Record> qryProductList(Cnds cnds);

    /**
     * 商品数目
     * @param cnds
     * @return
     */
    int countProductList(Cnds cnds);

    /**
     * 根据名称查询商品
     * @param name
     * @param id
     * @param f
     * @return
     */
    DcProduct qryProductByName(String name, int id, boolean f);

    /**
     * 保存商品
     * @param dcProduct
     */
    void insertProduct(DcProduct dcProduct);

    /**
     * 更新商品
     * @param dcProduct
     */
    void updateProduct(DcProduct dcProduct);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    DcProduct qryProductById(int id);

    /**
     * 查询所有商品综合数目
     * @return
     */
    int countProductListAll();

    /**
     * 导出商品
     * @param cnds
     * @return
     */
    List<Record> exportProductList(Cnds cnds);

    /**
     * 查询所有商品列表不包括删除
     * @return
     */
    List<DcProduct> qryProductListAll();

    /**
     * 查询未删除的商品
     * @return
     */
    List<DcProduct> qryProductListWithOutDelete();

    /**
     * 查询产品公司名称
     * @return
     */
    List<Record> qryProductCompanyAll();
}
