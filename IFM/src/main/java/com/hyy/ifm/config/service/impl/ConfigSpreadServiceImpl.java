package com.hyy.ifm.config.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigSpreadDao;
import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcConfig;
import com.hyy.ifm.config.service.ConfigSpreadService;
import com.hyy.ifm.news.dao.NewsDao;
import com.hyy.ifm.news.pojo.DcNews;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.frame.ProductType;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.Date;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:14
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "configSpreadService")
public class ConfigSpreadServiceImpl extends BaseService implements ConfigSpreadService {

    @Inject
    private ConfigSpreadDao configSpreadDao;
    @Inject
    private ProductDao productDao;
    @Inject
    private NewsDao newsDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qrySpreadConfigList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = configSpreadDao.qrySpreadConfigList(cnds);
        int count = configSpreadDao.countSpreadConfigList(cnds);
        for(Record record : res) {
            String associateId = record.getString("associate_id");
            String associateType = record.getString("associate_type");
            if("0".equals(associateType)) {
                //商品
                DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(associateId));
                record.set("associate_name", dcProduct.getName());
            } else if("1".equals(associateType)) {
                //资讯
                DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(associateId));
                record.set("associate_name", dcNews.getTitle());
            }
        }
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportSpreadConfigList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = configSpreadDao.exportSpreadConfigList(cnds);
        for(Record record : res) {
            String associateId = record.getString("associate_id");
            String associateType = record.getString("associate_type");
            if("0".equals(associateType)) {
                //商品
                DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(associateId));
                record.set("associate_name", dcProduct.getName());
            } else if("1".equals(associateType)) {
                //资讯
                DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(associateId));
                record.set("associate_name", dcNews.getTitle());
            }
        }
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean saveSpreadConfig(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getId()) ? true : false;

            DcConfig temp =  configSpreadDao.qrySpreadConfigByName(StringUtil.nvl(cnds.getRows().getName()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(temp!=null) {
                return this.joinformateJson(json, "该配置名称已经被使用，请重新输入", "");
            }

            if("0".equals(StringUtil.nvl(cnds.getRows().getPosition_dd())) && "0".equals(StringUtil.nvl(cnds.getRows().getStatus()))) {
                DcConfig dcConfig1 = configSpreadDao.qrySpreadConfigByOn();
                if(null != dcConfig1) {
                    return this.joinformateJson(json, "首页有且只能有一个上架的首页", "");
                }
            }

            DcConfig dcConfig = new DcConfig();
            dcConfig.setName(StringUtil.nvl(cnds.getRows().getName()));
            dcConfig.setPosition(StringUtil.nvl(cnds.getRows().getPosition_dd()));
            if(StringUtil.isNotBlank(cnds.getRows().getAssociate_type())) {
                dcConfig.setAssociateType(StringUtil.nvl(cnds.getRows().getAssociate_type()));
            } else {
                dcConfig.setAssociateType(null);
            }
            if(StringUtil.isNotBlank(cnds.getRows().getAssociate_id())) {
                dcConfig.setAssociateId(StringUtil.nvl(cnds.getRows().getAssociate_id()));
            } else {
                dcConfig.setAssociateId(null);
            }
            dcConfig.setPicPath(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcConfig.setUrl(StringUtil.nvl(cnds.getRows().getUrl()));
            dcConfig.setStartTime(DateUtil.StringToDate(StringUtil.nvl(cnds.getRows().getStart_time())));
            if(StringUtil.isNotBlank(cnds.getRows().getEnd_time())) {
                dcConfig.setEndTime(DateUtil.StringToDate(StringUtil.nvl(cnds.getRows().getEnd_time())));
            } else {
                dcConfig.setEndTime(null);
            }
            dcConfig.setRemark(StringUtil.nvl(cnds.getRows().getRemark()));
            dcConfig.setSort(StringUtil.nvl(cnds.getRows().getSort()));
            dcConfig.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                configSpreadDao.insertConfigSpread(dcConfig);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存配置");
                userDao.insertOperate(ifmUserOperate);
            } else {
                //更新
                dcConfig.setId(StringUtil.parseInt(cnds.getRows().getId()));
                configSpreadDao.updateConfigSpread(dcConfig);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改配置");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qrySpreadConfigById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcConfig dcConfig = configSpreadDao.qrySpreadConfigById(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcConfig);
    }

    @Override
    public CallBackBean updateSpreadConfig(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcConfig dcConfig = configSpreadDao.qrySpreadConfigById(StringUtil.parseInt(cnds.getRows().getId()));

            if(null != dcConfig) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if("1".equals(status)) {
                    //下架商品
                    if("1".equals(dcConfig.getStatus())) {
                        return this.joinformateJson(json, "配置已下架，请刷新后重试", "");
                    }
                    dcConfig.setStatus(status);
                } else if("0".equals(status)){
                    //上架商品
                    DcConfig dcConfig1 = configSpreadDao.qrySpreadConfigByOn();
                    if(null != dcConfig1) {
                        return this.joinformateJson(json, "首页有且只能有一个上架的首页", "");
                    }
                    if("0".equals(dcConfig.getStatus())) {
                        return this.joinformateJson(json, "配置已上架，请刷新后重试", "");
                    }
                    dcConfig.setStatus(status);
                } else if("2".equals(status)){
                    //删除商品
                    dcConfig.setStatus(status);
                }

                configSpreadDao.updateConfigSpread(dcConfig);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、删除配置");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "上下架、删除配置失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "上下架、删除配置失败", "");
        }
    }

}
