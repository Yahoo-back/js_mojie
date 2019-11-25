package com.hyy.root.controller;

import com.hyy.root.util.sharekey.SysShareKey;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpServletRequest;

@IocBean
@Ok("json")
@Fail("http:500")
@Filters(@By(type = CheckSession.class, args = { SysShareKey.LoginSession, SysShareKey.LoginUrl }))
public class WfmController extends BaseController {


    @At("/qudao/list")
    @Ok("jsp:qudao.list")
    public void qudao_list(HttpServletRequest req) {
        log.info(" 进入渠道人员的LIST.....");
    }

    @At("/qd/sl")
    @Ok("jsp:qudao.list_sl")
    public void qudao_sl(HttpServletRequest req) {
        log.info(" 进入渠道缩量的LIST.....");
    }

    @At("/qudao/count")
    @Ok("jsp:qudao.count")
    public void qudao_count(HttpServletRequest req) {
        log.info(" 进入渠道统计的LIST.....");
    }

    @At("/qudao/form_qudao")
    @Ok("jsp:qudao.form_qudao")
    public void qudaofrom_count(HttpServletRequest req) {
        log.info(" 进入渠道统计的LIST.....");
    }


    @At("/qudao/form_qdsl")
    @Ok("jsp:qudao.form_qdsl")
    public void qudaosl(HttpServletRequest req) {
        log.info(" 进入渠道缩量的LIST.....");
    }

    @At("/qudao/channel")
    @Ok("jsp:qudao.my_channel")
    public void qudao_channel(HttpServletRequest req) {
        log.info(" 进入我的渠道的LIST.....");
    }

    @At("/users/list")
    @Ok("jsp:user.list")
    public void user_list(HttpServletRequest req) {
        log.info(" 进入人员的LIST.....");
    }

    @At("/users/role_list")
    @Ok("jsp:user.rolelist")
    public void rolelist(HttpServletRequest req) {
        log.info(" 进入人员的LIST.....");
    }

    @At("/users/form_user")
    @Ok("jsp:user.form_user")
    public void form_user(HttpServletRequest req) {
        log.info(" 进入人员的form.....");
    }

    @At("/role/list")
    @Ok("jsp:role.list")
    public void role_list(HttpServletRequest req) {
        log.info(" 进入人员的form--gongzuoliu.....");
    }

    @At("/meun/list")
    @Ok("jsp:meun.list")
    public void meun_list(HttpServletRequest req) {
        log.info(" 进入人员的form--gongzuoliu.....");
    }

    @At("/main/changePass")
    @Ok("jsp:main.changePass")
    public void changePassWord() {
        log.info(" 进入修改用户密码.....");
    }

    @At("/product/list")
    @Ok("jsp:product.list_product")
    public void productList() {
        log.info(" 进入商品列表.....");
    }

    @At("/product/visitManage")
    @Ok("jsp:product.list_visit_product")
    public void productVisitManage() {
        log.info(" 进入商品访问管理.....");
    }

    @At("/product/form_product")
    @Ok("jsp:product.form_product")
    public void productFrom() {
        log.info(" 进入商品详情.....");
    }

    @At("/product/form_visit_product")
    @Ok("jsp:product.form_visit_product")
    public void productVisitFrom() {
        log.info(" 进入商品管理详情.....");
    }

    @At("/product/type/list")
    @Ok("jsp:product.list_type_product")
    public void productTypeList() {
        log.info(" 进入商品类型列表.....");
    }

    @At("/product/form_type_product")
    @Ok("jsp:product.form_type_product")
    public void productTypeFrom() {
        log.info(" 进入商品类型表单.....");
    }

    @At("/businessCustomer/list")
    @Ok("jsp:business.list_customer")
    public void businessList() {
        log.info(" 进入商务合作列表.....");
    }

    @At("/data/product/list")
    @Ok("jsp:data.list_product")
    public void dataProductList() {
        log.info(" 进入产品访问数据统计列表.....");
    }

    @At("/data/charts_product/list")
    @Ok("jsp:data.list_charts_product")
    public void dataChartsProductList() {
        log.info(" 进入产品访问数据统计图.....");
    }

    @At("/data/page/list")
    @Ok("jsp:data.list_page")
    public void pageList() {
        log.info(" 进入页面访问数据统计列表.....");
    }

    @At("/data/charts_page/list")
    @Ok("jsp:data.list_charts_page")
    public void pageChartsList() {
        log.info(" 进入页面访问统计图.....");
    }

    @At("/data/source/list")
    @Ok("jsp:data.list_source")
    public void sourceList() {
        log.info(" 进入点击来源据统计列表.....");
    }

    @At("/data/charts_source/list")
    @Ok("jsp:data.list_charts_source")
    public void pageSourceList() {
        log.info(" 进入点击来源据统计图.....");
    }

    @At("data/finance_report/forms")
    @Ok("jsp:data.finance_report_forms")
    public void financeReportForms() {
        log.info(" 进入财务报表.....");
    }

    @At("/customer/list")
    @Ok("jsp:customer.list_customer")
    public void customerList() {
        log.info(" 进入客户列表.....");
    }

    @At("/customer/sys")
    @Ok("jsp:customer.sys_customer")
    public void customerSys() { log.info(" 进入渠道计算汇总客户列表....."); }

    @At("/customer/form_customer")
    @Ok("jsp:customer.form_customer")
    public void customerFrom() {
        log.info(" 进入客户表单.....");
    }

    @At("/customer/update_customer")
    @Ok("jsp:customer.update_customer")
    public void customerUpdate() {
        log.info(" 修改客户表单.....");
    }

    @At("/data/report/forms")
    @Ok("jsp:data.report_forms")
    public void dataReportForms() {
        log.info(" 进入数据报表列表.....");
    }

    @At("/data/settle_product_tb")
    @Ok("jsp:data.cust_tb_forms")
    public void dataCust_tbForms() {
        log.info(" 进入结算统计列表.....");
    }

    @At("/data/count_product")
    @Ok("jsp:data.count_product_forms")
    public void dataCountProductForms() {
        log.info(" 进入数据报表统计列表.....");
    }

    @At("/data/channel")
    @Ok("jsp:data.channel_forms")
    public void dataChannelForms() {
        log.info(" 进入渠道报表统计列表.....");
    }

    @At("/config/spread/list")
    @Ok("jsp:config.list_spread_config")
    public void SpreadConfigList() {
        log.info(" 进入推广配置列表.....");
    }

    @At("/config/show/list")
    @Ok("jsp:config.list_show")
    public void list_show() {
        log.info(" 进入配置列表3.....");
    }

    @At("/config/form_spread_config")
    @Ok("jsp:config.form_spread_config")
    public void SpreadConfigFrom() {
        log.info(" 进入推广配置表单.....");
    }

    @At("/config/apply/require/list")
    @Ok("jsp:config.list_apply_require_config")
    public void ApplyRequireConfigList() {
        log.info(" 进入配置列表.....");
    }

    @At("/config/apply/data/list")
    @Ok("jsp:config.list_apply_data_config")
    public void ApplyDataConfigList() {
        log.info(" 进入配置列表.....");
    }

    @At("/config/price/interval/list")
    @Ok("jsp:config.list_price_interval_config")
    public void priceIntervalConfigList() {
        log.info(" 进入配置列表.....");
    }

    @At("/config/form_dict")
    @Ok("jsp:config.form_dict")
    public void dictFrom() {
        log.info(" 进入配置表单.....");
    }

    @At("/config/form_dictes")
    @Ok("jsp:config.form_dictes")
    public void dictFrom2() {
        log.info(" 进入配置表单2.....");
    }

    @At("/news/list")
    @Ok("jsp:news.list_news")
    public void newsList() {
        log.info(" 进入新闻列表.....");
    }

    @At("/news/form_news")
    @Ok("jsp:news.form_news")
    public void newsForm() {
        log.info(" 进入新闻表单.....");
    }

    @At("/news/classify/list")
    @Ok("jsp:news.list_classify_list")
    public void newsClassifyList() {
        log.info(" 进入分类管理.....");
    }

    @At("/news/label/list")
    @Ok("jsp:news.list_label_list")
    public void newsLabelList() {
        log.info(" 进入标签管理.....");
    }

    @At("/news/form_news_classify")
    @Ok("jsp:news.form_news_classify")
    public void newsFrom() {
        log.info(" 进入表单.....");
    }

    @At("/complaint/complaint/list")
    @Ok("jsp:complaint.list_complaint")
    public void complaintList() {
        log.info(" 进入客诉模块客户列表.....");
    }

    @At("/complaint/my_complaint/list")
    @Ok("jsp:complaint.list_my_complaint")
    public void complaintMyList() {
        log.info(" 进入客诉模块我的客户列表.....");
    }

    @At("/complaint/reback/list")
    @Ok("jsp:complaint.list_reback")
    public void complaintRebackList() {
        log.info(" 进入客诉模块退款列表.....");
    }

    @At("/complaint/email/list")
    @Ok("jsp:complaint.list_email")
    public void complaintListEmail() {
        log.info(" 进入客诉模块邮件退款列表.....");
    }

    @At("/complaint/reback/listStatistics")
    @Ok("jsp:complaint.list_reback_statistics")
    public void complaintRebackListStatistics() {
        log.info(" 进入客诉模块退款列表.....");
    }

    @At("/complaint/reback")
    @Ok("jsp:complaint.reback")
    public void complaintRebackMoney() {
        log.info(" 进入我的客户列表退款.....");
    }

    @At("/complaint/refuse")
    @Ok("jsp:complaint.refuse")
    public void complaintRefuse() {
        log.info(" 进入我的客户列表驳回.....");
    }

    @At("/complaint/form_complaint")
    @Ok("jsp:complaint.form_complaint")
    public void complaintFrom() {
        log.info(" 进入客户列表详情.....");
    }

    @At("/complaint/form_my_complaint")
    @Ok("jsp:complaint.form_my_complaint")
    public void complaintMyFrom() {
        log.info(" 进入我的客户详情.....");
    }

    @At("/customer/reback")
    @Ok("jsp:customer.reback")
    public void reback() {
        log.info(" 进入我的客户详情.....");
    }

    @At("/complaint/form_reback")
    @Ok("jsp:complaint.form_reback")
    public void complaintReback() {
        log.info(" 进入我的退款详情.....");
    }

    @At("/complaint/form_email")
    @Ok("jsp:complaint.form_email")
    public void complaintEmail() {
        log.info(" 进入邮件查看详情.....");
    }

}
