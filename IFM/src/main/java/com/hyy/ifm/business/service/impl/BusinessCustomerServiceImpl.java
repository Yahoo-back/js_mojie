package com.hyy.ifm.business.service.impl;

import com.hyy.ifm.business.dao.BusinessCustomerDao;
import com.hyy.ifm.business.pojo.Cnds;
import com.hyy.ifm.business.service.BusinessCustomerService;
import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 19:23
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "businessCustomerService")
public class BusinessCustomerServiceImpl extends BaseService implements BusinessCustomerService {

    @Inject
    private BusinessCustomerDao businessCustomerDao;

    @Override
    public CallBackBean qryBusinessCustomerList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = businessCustomerDao.qryBusinessCustomerList(cnds);
        int count = businessCustomerDao.countBusinessCustomerList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportBusinessCustomerList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = businessCustomerDao.exportBusinessCustomerList(cnds);
        return this.joinformateJson(json, "success", res);
    }

}
