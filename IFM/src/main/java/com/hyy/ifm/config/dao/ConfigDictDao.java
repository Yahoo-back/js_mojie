package com.hyy.ifm.config.dao;

import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcDict;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:50
 * @Description Created with IntelliJ IDEA.
 */
public interface ConfigDictDao {

    /**
     * 根据data_type查询字段表
     * @param data_type
     * @return
     */
    List<DcDict> qryDictByDataType(String data_type);

    /**
     * 根据data_type查询字段表
     * @param data_type
     * @return
     */
    List<DcDict> qryDictByDataShow();

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDictConfigList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countDictConfigList(Cnds cnds);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportDictConfigList(Cnds cnds);

    /**
     * 根据名称查询
     * @param name
     * @param id
     * @param f
     * @return
     */
    DcDict qryDictByName(String name, int id, boolean f);

    /**
     * 保存
     * @param dcDict
     */
    void insertConfigDict(DcDict dcDict);

    /**
     * 修改
     * @param dcDict
     */
    void updateConfigDict(DcDict dcDict);

    /**
     * 根据id查询
     * @param nvl
     * @return
     */
    DcDict qryDictById(String nvl);
}
