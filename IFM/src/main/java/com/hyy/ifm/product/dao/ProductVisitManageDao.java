package com.hyy.ifm.product.dao;

import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProductVisitManage;
import org.nutz.dao.entity.Record;

import java.util.List;


public interface ProductVisitManageDao {

    /**
     * 商品列表
     * @param cnds
     * @return
     */
    List<Record> qryProductVisitManageList(Cnds cnds);

    /**
     * 保存商品
     * @param dcProductVisitManage
     */
    void insertProductVisitManage(DcProductVisitManage dcProductVisitManage);

    /**
     * 更新商品
     * @param dcProductVisitManage
     */
    void updateProductVisitManage(DcProductVisitManage dcProductVisitManage);

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    DcProductVisitManage qryProductVisitManageById(int id);

    /**
     * 导出商品
     * @param cnds
     * @return
     */
    List<Record> exportProductList(Cnds cnds);

    /**
     * 查询商品当天访问数
     * @param
     * @return
     */
    Record qryProductVisitCount(int id);

    /**
     * 查询所有商品列表不包括删除和已管理的
     * @return
     */
    List<Record> qryVisitProductListAll();

}
