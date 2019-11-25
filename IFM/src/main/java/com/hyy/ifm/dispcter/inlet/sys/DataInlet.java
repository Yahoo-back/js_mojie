package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.data.service.*;
import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:10
 * @Description 报表管理
 */
@IocBean(scope="singleton", singleton=true)
public class DataInlet extends Inlet {

    private static final long serialVersionUID = -7186875585131914442L;

    @Inject
    private DataProductService dataProductService;
    @Inject
    private DataPageService dataPageService;
    @Inject
    private DataReportService dataReportService;
    @Inject
    private DataSourceService dataSourceService;
    @Inject
    private DataChannelService dataChannelService;
    @Override
    public void init() {

        /**
         * 产品访问统计表
         */
        serviceTreeMap.put("qryDataProductList", dataProductService);

        /**
         * 产品访问统计表
         */
        serviceTreeMap.put("qryDataProductListBy1", dataProductService);


        /**
         * 产品访问统计表
         */
        serviceTreeMap.put("qryDataProductListBy2", dataProductService);

        /**
         * 导出产品访问统计表
         */
        serviceTreeMap.put("exportDataProductList", dataProductService);


        /**
         * 导出产品访问统计表
         */
        serviceTreeMap.put("qryQuDaoDataSourceList", dataSourceService);

        /**
         * 导出产品访问统计表
         */
        serviceTreeMap.put("qryQuDaoDataSourceSum", dataSourceService);

        /**
         * 导出产品访问统计表
         */
        serviceTreeMap.put("qryQuDaoSl", dataSourceService);

        /**
         * 根据id查询产品列表数据
         */
        serviceTreeMap.put("qryDataEchartsProductById", dataProductService);

        /**
         * 导出产品访问统计图
         */
        serviceTreeMap.put("exportDataProductCharts", dataProductService);


        /**
         * 查询所有页面分类
         */
        serviceTreeMap.put("qryPageListAll", dataPageService);

        /**
         * 页面访问数据统计
         */
        serviceTreeMap.put("qryDataPageList", dataPageService);

        /**
         * 导出页面访问数据统计
         */
        serviceTreeMap.put("exportDataPageList", dataPageService);

        /**
         * 页面访问统计图
         */
        serviceTreeMap.put("qryDataPageCharts", dataPageService);

        /**
         * 导出页面访问统计
         */
        serviceTreeMap.put("exportDataPageCharts", dataPageService);

        /**
         * 数据报表列表
         */
        serviceTreeMap.put("qryDataReportList", dataReportService);

        /**
         * 财务报表列表
         */
        serviceTreeMap.put("qryDataFinanceReportList", dataChannelService);

        /**
         * 我的渠道列表
         */
        serviceTreeMap.put("qryMyChannelList", dataChannelService);

        /**
         * 统计注册个数和放款个数
         */
        serviceTreeMap.put("countProductInfo", dataReportService);

        /**
         * 结算
         */
        serviceTreeMap.put("settleProduct", dataReportService);

        /**
         * 结算统计
         */
        serviceTreeMap.put("settleProductInfo", dataReportService);

        /**
         * 导出数据报表
         */
        serviceTreeMap.put("exportDataProductReportList", dataReportService);

        /**
         * 导出财务报表
         */
        serviceTreeMap.put("exportDataFinanceReportList", dataReportService);

        /**
         * 统计
         */
        serviceTreeMap.put("queryDataProductReportTotal", dataReportService);

        /**
         * 查询统计信息
         */
        serviceTreeMap.put("qryCountProductInfo", dataReportService);



        /**
         * 点击来源数据统计
         */
        serviceTreeMap.put("qryDataSourceList", dataSourceService);

        /**
         * 点击来源数据统计
         */
        serviceTreeMap.put("qryDataSourceSum", dataSourceService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportDataSourceList", dataSourceService);

        /**
         * 查询所有来源
         */
        serviceTreeMap.put("qrySourceAll", dataSourceService);

        /**
         * 查询我的渠道
         */
        serviceTreeMap.put("qryMySourceAll", dataSourceService);

        /**
         * 统计图
         */
        serviceTreeMap.put("qryDataEchartsSourceBySource", dataSourceService);

        /**
         * 导出统计图
         */
        serviceTreeMap.put("exportDataSourceCharts", dataSourceService);

        /**
         * 结算表单
         */
        serviceTreeMap.put("qrySettleProductInfo", dataReportService);

        /**
         * 所有合作方式
         */
        serviceTreeMap.put("qryProductSettleWayAll", dataReportService);
    }

}
