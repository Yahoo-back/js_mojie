package com.hyy.ifm.config.dao;

import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcConfig;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:15
 * @Description Created with IntelliJ IDEA.
 */
public interface ConfigSpreadDao {

    /**
     * 查询推广配置列表
     * @param cnds
     * @return
     */
    List<Record> qrySpreadConfigList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countSpreadConfigList(Cnds cnds);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportSpreadConfigList(Cnds cnds);

    /**
     * 根据名称查询推广配置
     * @param name
     * @param id
     * @param f
     * @return
     */
    DcConfig qrySpreadConfigByName(String name, int id, boolean f);

    /**
     * 查询上架的首页
     * @return
     */
    DcConfig qrySpreadConfigByOn();

    /**
     * 保存
     * @param dcConfig
     */
    void insertConfigSpread(DcConfig dcConfig);

    /**
     * 更新
     * @param dcConfig
     */
    void updateConfigSpread(DcConfig dcConfig);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    DcConfig qrySpreadConfigById(int id);
}
