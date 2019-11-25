package com.hyy.ifm.data.dao;

import com.hyy.ifm.data.pojo.Cnds;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:45
 * @Description Created with IntelliJ IDEA.
 */
public interface DataProductDao {



    /**
     * 根据渠道查询产品访问
     * @param cnds
     * @return
     */
    Record qryDataProductBySource(Cnds cnds);
    /**
     * 产品访问统计表
     * @param cnds
     * @return
     */
    List<Record> qryDataProductList(Cnds cnds);

    /**
     * 产品访问统计表上架
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceListBy1(Cnds cnds);

    /**
     * 产品访问统计表下架
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceListBy2(Cnds cnds);

    /**
     * 产品访问统计表上架数目
     * @param cnds
     * @return
     */
    int countDataSourceListBy1(Cnds cnds);

    /**
     * 产品访问统计表下架数目
     * @param cnds
     * @return
     */
    int countDataSourceListBy2(Cnds cnds);

    /**
     * 产品访问统计表数目
     * @param cnds
     * @return
     */
    int countDataProductList(Cnds cnds);

    /**
     * 产品访问统计表数目
     * @param cnds
     * @return
     */
    int countBy2(Cnds cnds);

    /**
     * 产品访问统计表数目
     * @param cnds
     * @return
     */
    int countBy1(Cnds cnds);

    /**
     * 导出产品访问统计表
     * @param cnds
     * @return
     */
    List<Record> exportDataProductList(Cnds cnds);

    /**
     * 产品访问统计
     * @param cnds
     * @param productId
     * @return
     */
    List<Record> qryDataEchartsProductById(Cnds cnds, String productId);
}
