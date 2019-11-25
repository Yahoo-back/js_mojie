package com.hyy.ifm.data.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.data.dao.DataProductDao;
import com.hyy.ifm.data.pojo.Cnds;
import com.hyy.ifm.data.service.DataProductService;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.DateUtil;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-11 10:44
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "dataProductService")
public class DataProductServiceImpl extends BaseService implements DataProductService {

    @Inject
    private DataProductDao dataProductDao;
    @Inject
    private ProductDao productDao;

    @Override
    public CallBackBean qryDataProductList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = dataProductDao.qryDataProductList(cnds);
        int count = dataProductDao.countDataProductList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean qryDataProductListBy1(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = dataProductDao.qryDataSourceListBy1(cnds);
        int count = dataProductDao.countDataSourceListBy1(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean qryDataProductListBy2(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = dataProductDao.qryDataSourceListBy2(cnds);
        int count = dataProductDao.countDataSourceListBy2(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportDataProductList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = dataProductDao.exportDataProductList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qryDataEchartsProductById(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            int productId = StringUtil.parseInt(cnds.getRows().getProductId());
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }

            cnds = SqlUtils.apCnd3(cnds);
            List<Record> records = dataProductDao.qryDataEchartsProductById(cnds, "AND dpvl.product_id = '" + productId +"'");
            DcProduct dcProduct = productDao.qryProductById(productId);
            for(Record record : records) {
                record.set("productName", dcProduct.getName());
            }
            return this.joinformateJson(json, "success", records);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "查询失败", "");
        }
    }

    @Override
    public CallBackBean exportDataProductCharts(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            String productIds = StringUtil.nvl(cnds.getRows().getProductId());
            String daysFrom = StringUtil.nvl(cnds.getRows().getDaysFrom());
            String daysTo = StringUtil.nvl(cnds.getRows().getDaysTo());
            if(StringUtil.isNotBlank(daysFrom) && StringUtil.isNotBlank(daysTo)) {
                if(DateUtil.StringToDate(daysTo + ":00:00").getTime() < DateUtil.StringToDate(daysFrom + ":00:00").getTime()) {
                    return this.joinformateJson(json, "起始时间不能小于终止时间", "");
                }
            }
            String[] productIdsArray = null;

            if(StringUtil.isNotBlank(productIds)) {
                productIdsArray = productIds.split(",");
            } else {
                List<DcProduct> dcProducts = productDao.qryProductListAll();
                StringBuilder temp = new StringBuilder();
                for(int i=0; i<dcProducts.size(); i++) {
                    temp.append(dcProducts.get(i).getId());
                    if(i != dcProducts.size() - 1) {
                        temp.append(",");
                    }
                }
                productIdsArray = temp.toString().split(",");
            }
            cnds = SqlUtils.apCnd3(cnds);
            List<Record> res = new ArrayList<Record>();

            int count = 0;
            for(String id : productIdsArray) {
                count++;
                List<Record> records = dataProductDao.qryDataEchartsProductById(cnds, "AND dpvl.product_id = '" + id + "'");
                //先将日期存入 只存入一次
                for(int i=0; i<records.size(); i++) {
                    if(count == 1) {
                        Record record1 = new Record();
                        record1.set("click_date", records.get(i).getString("click_date"));
                        res.add(record1);
                    }
                }

                //数据与日期符合 存入数据
                for(Record record : res) {
                    for(Record t : records) {
                        if(record.getString("click_date").equals(t.getString("click_date"))) {
                            record.set("product_" + id, t.getString("count"));
                        }
                    }
                }
            }
            return this.joinformateJson(json, "success", res);
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "导出失败", "");
        }
    }

}
