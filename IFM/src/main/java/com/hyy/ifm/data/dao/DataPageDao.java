package com.hyy.ifm.data.dao;

import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcVisitLog;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 11:14
 * @Description Created with IntelliJ IDEA.
 */
public interface DataPageDao {
    /**
     * 页面访问数据统计
     * @param cnds
     * @return
     */
    List<Record> qryDataProductList(Cnds cnds);

    /**
     * 页面访问数目
     * @param cnds
     * @return
     */
    int countDataProductList(Cnds cnds);

    /**
     * 导出页面访问数据统计
     * @param cnds
     * @return
     */
    List<Record> exportDataPageList(Cnds cnds);

    /**
     * 页面访问统计图
     * @param cnds
     * @param classify
     * @return
     */
    List<Record> qryDataPageCharts(Cnds cnds, String classify);

    /**
     * 所有页面类型列表
     * @return
     */
    List<Record> qryPageListAll();
}
