package com.hyy.ifm.product.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.dao.ProductVisitManageDao;
import com.hyy.ifm.product.frame.ProductType;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProductVisitManage;
import com.hyy.ifm.product.service.ProductVisitManageService;
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


@IocBean(name = "ProductVisitManageService")
public class ProductVisitManageServiceImpl extends BaseService implements ProductVisitManageService {

    @Inject
    private ProductVisitManageDao productVisitManageDao;

    @Inject
    private ProductDao productDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryProductVisitManageList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd6(cnds);
        List<Record> res = productVisitManageDao.qryProductVisitManageList(cnds);
        return this.joinformateJson(json, "success", res.size(), res);
    }

    @Override
    public CallBackBean saveProductVisitManage(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;

            //dc_product商品
            DcProductVisitManage dcProductVisitManage = new DcProductVisitManage();
            dcProductVisitManage.setProductId(StringUtil.parseInt(cnds.getRows().getClassify_id()));
            dcProductVisitManage.setCount(StringUtil.parseInt(cnds.getRows().getCount()));
            dcProductVisitManage.setMoney(StringUtil.nvl(cnds.getRows().getMoney()));
            if( null != cnds.getRows().getMoney()){
                dcProductVisitManage.setIsMoney("0");
            }
            dcProductVisitManage.setStatus("0");

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcProductVisitManage.setCreateTime(new Date());
                productVisitManageDao.insertProductVisitManage(dcProductVisitManage);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存商品" + StringUtil.parseInt(cnds.getRows().getClassify_id()) );
                userDao.insertOperate(ifmUserOperate);
            } else {
                //更新
                dcProductVisitManage.setId(StringUtil.parseInt(cnds.getRows().getId()));
                productVisitManageDao.updateProductVisitManage(dcProductVisitManage);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改商品" + StringUtil.parseInt(cnds.getRows().getClassify_id()));
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryProductVisitManageById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcProductVisitManage dcProductVisitManage = productVisitManageDao.qryProductVisitManageById(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcProductVisitManage);
    }

    @Override
    public CallBackBean exportProductList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = productDao.exportProductList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qryProductVisitCount(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        Record count = productVisitManageDao.qryProductVisitCount(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", count);
    }

    @Override
    public CallBackBean updateProductVisitManage(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcProductVisitManage dcProductVisitManage = productVisitManageDao.qryProductVisitManageById(StringUtil.parseInt(cnds.getRows().getId()));

            if(null != dcProductVisitManage) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if(ProductType.UNDER_GOODS.equals(status)) {
                    //开启
                    if(ProductType.UNDER_GOODS.equals(dcProductVisitManage.getStatus())) {
                        return this.joinformateJson(json, "已开启，请刷新后重试", "");
                    }
                    dcProductVisitManage.setStatus("0");
                } else if(ProductType.PUT_GOODS.equals(status)){
                    //关闭
                    if(ProductType.PUT_GOODS.equals(dcProductVisitManage.getStatus())) {
                        return this.joinformateJson(json, "已关闭，请刷新后重试", "");
                    }
                    dcProductVisitManage.setStatus("1");
                }

                productVisitManageDao.updateProductVisitManage(dcProductVisitManage);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("开启关闭商品访问管理");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "开启/关闭失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "开启/关闭失败", "");
        }
    }

    @Override
    public CallBackBean qryVisitProductListAll(String json) {
        List<Record> dcProducts = productVisitManageDao.qryVisitProductListAll();
        return this.joinformateJson(json, "success", dcProducts);
    }
}
