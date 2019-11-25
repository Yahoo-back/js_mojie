package com.hyy.ifm.data.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.data.dao.DataPageDao;
import com.hyy.ifm.data.frame.ClassifyType;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.pojo.DcVisitLog;
import com.hyy.ifm.data.service.DataPageService;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 11:13
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataPageService")
public class DataPageServiceImpl extends BaseService implements DataPageService {

    @Inject
    private DataPageDao dataPageDao;

    @Override
    public CallBackBean qryPageListAll(String json) {
        List<Record> dcVisitLogs = dataPageDao.qryPageListAll();
        return this.joinformateJson(json, "success", dcVisitLogs);
    }

    @Override
    public CallBackBean qryDataPageList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = dataPageDao.qryDataProductList(cnds);
        int count = dataPageDao.countDataProductList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportDataPageList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = dataPageDao.exportDataPageList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qryDataPageCharts(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String type = StringUtil.nvl(cnds.getRows().getType());
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }

            cnds = SqlUtils.apCnd3(cnds);
            List<Record> records = dataPageDao.qryDataPageCharts(cnds, "AND dvl.classify = '" + type +"'");
            if(ClassifyType.HOME_PAGE.equals(type)) {
                for(Record record : records) {
                    record.set("classifyName", "首页");
                }
            } else if(ClassifyType.REGISTER_PAGE.equals(type)){
                for(Record record : records) {
                    record.set("classifyName", "注册页");
                }
            } else if(ClassifyType.REGISTER_COUNT.equals(type)) {
                for(Record record : records) {
                    record.set("classifyName", "注册量");
                }
            }
            return this.joinformateJson(json, "success", records);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询或导出失败", "");
        }
    }

    @Override
    public CallBackBean exportDataPageCharts(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }

            List<Record> records = null;
            if(StringUtil.isBlank(daysFrom) && StringUtil.isBlank(daysTo)) {
                //默认展示7小时内的注册统计图
                cnds = SqlUtils.apCnd3(cnds);
                records = dataPageDao.qryDataPageCharts(cnds, "AND dvl.classify = '0'");
            } else if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                //选时间选择
                cnds = SqlUtils.apCnd3(cnds);
                records = dataPageDao.qryDataPageCharts(cnds, "AND dvl.classify = '0'");
            }
            List<Record> records2 = dataPageDao.qryDataPageCharts(cnds, "AND dvl.classify = '1'");
            for(Record record : records) {
                for(Record record1 : records2) {
                    if(record.getString("click_date").equals(record1.getString("click_date"))) {
                        record.set("count2", record1.getString("count"));
                    }
                }
            }

            List<Record> records3 = dataPageDao.qryDataPageCharts(cnds, "AND dvl.classify = '2'");
            for(Record record : records) {
                for(Record record1 : records3) {
                    if(record.getString("click_date").equals(record1.getString("click_date"))) {
                        record.set("count3", record1.getString("count"));
                    }
                }
            }

            return this.joinformateJson(json, "success", records);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询失败", "");
        }
    }

}
