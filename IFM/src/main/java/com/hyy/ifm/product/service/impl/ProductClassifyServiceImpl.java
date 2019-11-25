package com.hyy.ifm.product.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.product.dao.ProductClassifyDao;
import com.hyy.ifm.product.frame.ProductType;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProductClassify;
import com.hyy.ifm.product.service.ProductClassifyService;
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
 * @Date 2018-03-26 10:10
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
public class ProductClassifyServiceImpl extends BaseService implements ProductClassifyService {

    @Inject
    private ProductClassifyDao productClassifyDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryProductClassifyAll(String json) {
        List<DcProductClassify> dcProductClassifies =  productClassifyDao.qryProductClassifyAll();
        return this.joinformateJson(json, "success", dcProductClassifies);
    }

    @Override
    public CallBackBean qryProductClassifyList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = productClassifyDao.qryProductClassifyList(cnds);
        int count = productClassifyDao.countProductClassifyList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportProductClassifyList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = productClassifyDao.exportProductClassifyList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean saveProductClassify(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            boolean f = "".equals(cnds.getRows().getId()) ? true : false;

            DcProductClassify temp = productClassifyDao.qryProductClassifyByName(StringUtil.nvl(cnds.getRows().getName()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(null != temp) {
                return this.joinformateJson(json, "该商品类型名称已经被使用，请重新输入", "");
            }

            DcProductClassify dcProductClassify = new DcProductClassify();
            dcProductClassify.setName(StringUtil.nvl(cnds.getRows().getName()));
            dcProductClassify.setUrl(StringUtil.nvl(cnds.getRows().getUrl()));
            dcProductClassify.setLogo(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcProductClassify.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));
            dcProductClassify.setSort(StringUtil.nvl(cnds.getRows().getSort()));
            dcProductClassify.setIsHome(StringUtil.nvl(cnds.getRows().getIs_home()));
            dcProductClassify.setHomeSort(StringUtil.nvl(cnds.getRows().getHome_sort()));


            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
            if(f) {
                //保存
                dcProductClassify.setCreateTime(new Date());
                productClassifyDao.insertProductClassify(dcProductClassify);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存商品类型");
                userDao.insertOperate(ifmUserOperate);
            } else {
                //更新
                dcProductClassify.setId(StringUtil.parseInt(cnds.getRows().getId()));
                productClassifyDao.updateProductClassify(dcProductClassify);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改商品类型");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryProductClassifyById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcProductClassify dcProductClassify = productClassifyDao.qryProductClassifyById(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcProductClassify);
    }

    @Override
    public CallBackBean updateProductClassify(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcProductClassify dcProductClassify = productClassifyDao.qryProductClassifyById(StringUtil.parseInt(cnds.getRows().getId()));

            if(null != dcProductClassify) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if("1".equals(status)) {
                    //下架商品类型
                    if("1".equals(dcProductClassify.getStatus())) {
                        return this.joinformateJson(json, "该商品类型已下架，请刷新后重试", "");
                    }
                    dcProductClassify.setStatus(status);
                } else if("0".equals(status)){
                    //上架商品类型
                    if("0".equals(dcProductClassify.getStatus())) {
                        return this.joinformateJson(json, "该商品类型已上架，请刷新后重试", "");
                    }
                    dcProductClassify.setStatus(status);
                } else if(ProductType.DELETE_GOODS.equals(status)){
                    //删除商品类型
                    dcProductClassify.setStatus(status);
                }

                productClassifyDao.updateProductClassify(dcProductClassify);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、删除商品类型");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "上下架、删除商品类型失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "上下架、删除商品类型失败", "");
        }
    }

}
