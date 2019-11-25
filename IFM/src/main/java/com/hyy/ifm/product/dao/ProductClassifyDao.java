package com.hyy.ifm.product.dao;

import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProductClassify;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 10:13
 * @Description Created with IntelliJ IDEA.
 */
public interface ProductClassifyDao {

    /**
     * 查询产品类型列表
     * @return
     */
    List<DcProductClassify> qryProductClassifyAll();

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryProductClassifyList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countProductClassifyList(Cnds cnds);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportProductClassifyList(Cnds cnds);

    /**
     * 根据名称查询
     * @param name
     * @param id
     * @param f
     * @return
     */
    DcProductClassify qryProductClassifyByName(String name, int id, boolean f);

    /**
     * 保存
     * @param dcProductClassify
     */
    void insertProductClassify(DcProductClassify dcProductClassify);

    /**
     * 更新
     * @param dcProductClassify
     */
    void updateProductClassify(DcProductClassify dcProductClassify);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    DcProductClassify qryProductClassifyById(int id);
}
