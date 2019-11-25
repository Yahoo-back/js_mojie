package com.hyy.ifm.news.dao;

import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNewsType;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 15:01
 * @Description Created with IntelliJ IDEA.
 */
public interface NewsClassifyDao {
    /**
     * 查询所有分类
     * @return
     */
    List<DcNewsType> qryNewsClassifyAll(String type);

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryNewsTypeList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countNewsTypeList(Cnds cnds);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportNewsTypeList(Cnds cnds);

    /**
     * 根据名称查询
     * @param nvl
     * @param i
     * @param f
     * @return
     */
    DcNewsType qryClassifyByName(String nvl, int i, boolean f);

    /**
     * 保存
     * @param dcNewsType
     */
    void insertClassify(DcNewsType dcNewsType);

    /**
     * 更新
     * @param dcNewsType
     */
    void updateClassify(DcNewsType dcNewsType);

    /**
     * 根据id查询
     * @param i
     * @return
     */
    DcNewsType qryClassifyById(int i);

    /**
     * 删除关联信息
     * @param id
     * @param type
     */
    void deleteAssociate(int id, String type);
}
