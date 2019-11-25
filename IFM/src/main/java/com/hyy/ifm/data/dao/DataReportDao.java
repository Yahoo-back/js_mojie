package com.hyy.ifm.data.dao;

import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcProductReport;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-19 15:21
 * @Description Created with IntelliJ IDEA.
 */
public interface DataReportDao {

    /**
     * 数据报表列表
     * @param cnds
     * @return
     */
    List<Record> qryDataReportList(Cnds cnds);

    /**
     * 财务报表列表
     * @param cnds
     * @return
     */
    List<Record> qryDataFinanceReportList(Cnds cnds);

    /**
     * 数据报表数目
     * @param cnds
     * @return
     */
    int countDataReportList(Cnds cnds);

    /**
     * 根据id查询产品数据报告
     * @param reportId
     * @return
     */
    DcProductReport fetchProductReportById(int reportId);

    /**
     * 更新
     * @param dcProductReport
     */
    void updateProductReport(DcProductReport dcProductReport);

    /**
     * 导出数据报表
     * @param cnds
     * @return
     */
    List<Record> exportDataProductReportList(Cnds cnds);

    /**
     * 导出财务报表
     * @param cnds
     * @return
     */
    List<Record> exportDataFinanceReportList(Cnds cnds);

    /**
     * 统计
     * @param cnds
     * @return
     */
    List<Record> queryDataProductReportTotal(Cnds cnds);

    /**
     * 插入数据
     * @param dcProductReport
     */
    void insetProductReport(DcProductReport dcProductReport);

    /**
     * 查询产品当日点击次数
     * @param id
     * @return
     */
    Record countDayOfVisitCountByProduct(int id);

    /**
     * 根据id查询统计信息
     * @param id
     * @return
     */
    DcProductReport qryCountProductInfo(String id);

    /**
     * 查询所有结算方式
     * @param json
     * @return
     */
    List<Record> qryProductSettleWayAll(String json);
}
