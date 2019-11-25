package com.hyy.ifm.config.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.config.service.ConfigDictService;
import com.hyy.ifm.product.frame.ProductType;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.Date;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:45
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
public class ConfigDictServiceImpl extends BaseService implements ConfigDictService {

    @Inject
    private ConfigDictDao configDictDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryDictByDataType(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<DcDict> dcDict = configDictDao.qryDictByDataType(StringUtil.nvl(cnds.getRows().getData_type()));
        return this.joinformateJson(json, "success", dcDict);
    }

    @Override
    public CallBackBean qryDictConfigList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = configDictDao.qryDictConfigList(cnds);
        int count = configDictDao.countDictConfigList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportDictConfigList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = configDictDao.exportDictConfigList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean saveConfigDict(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;
            DcDict temp = configDictDao.qryDictByName(StringUtil.nvl(cnds.getRows().getItem_value()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(null != temp) {
                return this.joinformateJson(json, "该名称已经被使用，请重新输入", "");
            }

            DcDict dcDict = new DcDict();
            dcDict.setDataType(StringUtil.nvl(cnds.getRows().getData_type()));
            dcDict.setItemValue(StringUtil.nvl(cnds.getRows().getItem_value()));
            dcDict.setIsUse(StringUtil.nvl(cnds.getRows().getIs_use()));
            dcDict.setXh(StringUtil.nvl(cnds.getRows().getXh()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcDict.setItemKey("0");
                configDictDao.insertConfigDict(dcDict);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存配置");
                userDao.insertOperate(ifmUserOperate);

            } else {
                //更新
                dcDict.setId(StringUtil.parseInt(cnds.getRows().getId()));
                configDictDao.updateConfigDict(dcDict);

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
    public CallBackBean saveConfig(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;
            DcDict temp = configDictDao.qryDictByName(StringUtil.nvl(cnds.getRows().getItem_value()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(null != temp) {
                return this.joinformateJson(json, "该名称已经被使用，请重新输入", "");
            }

            DcDict dcDict = new DcDict();
            //dcDict.setDataType(StringUtil.nvl(cnds.getRows().getData_type()));
            dcDict.setItemValue(StringUtil.nvl(cnds.getRows().getItem_value()));
            //dcDict.setIsUse(StringUtil.nvl(cnds.getRows().getIs_use()));
            dcDict.setXh(StringUtil.nvl(cnds.getRows().getXh()));
            dcDict.setItemKey(StringUtil.nvl(cnds.getRows().getItem_key()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcDict.setItemKey("0");
                configDictDao.insertConfigDict(dcDict);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存配置");
                userDao.insertOperate(ifmUserOperate);

            } else {
                //更新
                dcDict.setId(StringUtil.parseInt(cnds.getRows().getId()));
                configDictDao.updateConfigDict(dcDict);

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
    public CallBackBean qryDictById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcDict dcDict = configDictDao.qryDictById(StringUtil.nvl(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcDict);
    }

    @Override
    public CallBackBean qryDictByShow(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<DcDict> dcDicts = configDictDao.qryDictByDataShow();

        return this.joinformateJson(json, "success", dcDicts);
    }

    @Override
    public CallBackBean updateConfigDict(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcDict dcDict = configDictDao.qryDictById(StringUtil.nvl(cnds.getRows().getId()));

            if(null != dcDict) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if("0".equals(status)) {
                    //下架
                    if("0".equals(dcDict.getIsUse())) {
                        return this.joinformateJson(json, "配置已下架，请刷新后重试", "");
                    }
                    dcDict.setIsUse(status);
                } else if("1".equals(status)){
                    //上架
                    if("1".equals(dcDict.getIsUse())) {
                        return this.joinformateJson(json, "配置已上架，请刷新后重试", "");
                    }
                    dcDict.setIsUse(status);
                } else if("2".equals(status)){
                    //删除
                    dcDict.setIsUse(status);
                }

                configDictDao.updateConfigDict(dcDict);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、配置商品");
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
