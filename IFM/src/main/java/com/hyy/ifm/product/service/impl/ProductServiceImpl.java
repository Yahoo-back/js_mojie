package com.hyy.ifm.product.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.product.dao.ProductDao;
import com.hyy.ifm.product.frame.ProductType;
import com.hyy.ifm.product.pojo.Cnds;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.product.service.ProductService;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.StringUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 16:20
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "productService")
public class ProductServiceImpl extends BaseService implements ProductService {

    @Inject
    private ProductDao productDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryProductList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = productDao.qryProductList(cnds);
        int count = productDao.countProductList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean saveProduct(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;
            DcProduct temp = productDao.qryProductByName(StringUtil.nvl(cnds.getRows().getName()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(temp!=null) {
                return this.joinformateJson(json, "该商品名称已经被使用，请重新输入", "");
            }

            //dc_product商品
            DcProduct dcProduct = new DcProduct();
            dcProduct.setName(StringUtil.nvl(cnds.getRows().getName()));
            dcProduct.setLogo(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcProduct.setClassifyId(StringUtil.parseInt(cnds.getRows().getClassify_id()));
            dcProduct.setInterest(StringUtil.nvl(cnds.getRows().getInterest()));
            dcProduct.setMoney(StringUtil.nvl(cnds.getRows().getMoney()));
            dcProduct.setLink(StringUtil.nvl(cnds.getRows().getLink()));
            dcProduct.setPerioWay(StringUtil.nvl(cnds.getRows().getPerio_way()));
            dcProduct.setPeriodization(StringUtil.nvl(cnds.getRows().getPeriodization()));
            dcProduct.setDescription(StringUtil.nvl(cnds.getRows().getDescription()));
            dcProduct.setSettleWayCpa(StringUtil.nvl(cnds.getRows().getSettle_way_cpa()));
            dcProduct.setSettleWayCps(StringUtil.nvl(cnds.getRows().getSettle_way_cps()));
            dcProduct.setSettleCycle(StringUtil.nvl(cnds.getRows().getSettle_cycle()));
            dcProduct.setConditionList(StringUtil.nvl(cnds.getRows().getApply_require()));
            dcProduct.setDataList(StringUtil.nvl(cnds.getRows().getApply_data()));
            dcProduct.setKtxDesc(StringUtil.nvl(cnds.getRows().getKtx_desc()));
            dcProduct.setManagerUrl(StringUtil.nvl(cnds.getRows().getManager_url()));
            dcProduct.setManagerUser(StringUtil.nvl(cnds.getRows().getManager_user()));
            dcProduct.setManagerPassword(StringUtil.nvl(cnds.getRows().getManager_password()));
            dcProduct.setSort(StringUtil.parseInt(cnds.getRows().getSort()));
            dcProduct.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));
            dcProduct.setIsHot(StringUtil.nvl(cnds.getRows().getIs_hot()));
            dcProduct.setHotSort(StringUtil.nvl(cnds.getRows().getHot_sort()));
            dcProduct.setContact(StringUtil.nvl(cnds.getRows().getContact()));
            dcProduct.setContactInfo(StringUtil.nvl(cnds.getRows().getContact_info()));
            dcProduct.setCompany(StringUtil.nvl(cnds.getRows().getCompany()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcProduct.setPosition("0");
                dcProduct.setApplyNum("0");
                dcProduct.setCreateTime(new Date());

                productDao.insertProduct(dcProduct);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存商品");
                userDao.insertOperate(ifmUserOperate);
            } else {
                //更新
                dcProduct.setId(StringUtil.parseInt(cnds.getRows().getId()));
                productDao.updateProduct(dcProduct);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改商品");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryProductById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcProduct);
    }

    @Override
    public CallBackBean updateProduct(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(cnds.getRows().getId()));

            if(null != dcProduct) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if(ProductType.UNDER_GOODS.equals(status)) {
                    //下架商品
                    if(ProductType.UNDER_GOODS.equals(dcProduct.getStatus())) {
                        return this.joinformateJson(json, dcProduct.getName() + "商品已下架，请刷新后重试", "");
                    }
                    dcProduct.setStatus("0");
                } else if(ProductType.PUT_GOODS.equals(status)){
                    //上架商品
                    if(ProductType.PUT_GOODS.equals(dcProduct.getStatus())) {
                        return this.joinformateJson(json, dcProduct.getName() + "商品已上架，请刷新后重试", "");
                    }
                    dcProduct.setStatus("1");
                } else if(ProductType.DELETE_GOODS.equals(status)){
                    //删除商品
                    dcProduct.setStatus("2");
                }

                productDao.updateProduct(dcProduct);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、删除商品");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "上下架、删除商品失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "上下架、删除商品失败", "");
        }
    }

    @Override
    public CallBackBean topPosition(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(cnds.getRows().getId()));
            if(null != dcProduct) {
                dcProduct.setPosition("1");
                dcProduct.setSort(0);
                dcProduct.setHotSort("0");
                productDao.updateProduct(dcProduct);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("置顶商品");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "置顶商品失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "置顶商品失败", "");
        }
    }

    @Override
    public CallBackBean basePosition(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(cnds.getRows().getId()));
            if(null != dcProduct) {
                dcProduct.setPosition("2");
                int count = productDao.countProductListAll();
                dcProduct.setSort(count+1);
                dcProduct.setHotSort(String.valueOf(count+1));
                productDao.updateProduct(dcProduct);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("置尾商品");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "置尾商品", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "置尾商品失败", "");
        }
    }

    @Override
    public CallBackBean cancelPosition(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            DcProduct dcProduct = productDao.qryProductById(StringUtil.parseInt(cnds.getRows().getId()));
            if(null != dcProduct) {
                dcProduct.setPosition("0");
                int count = productDao.countProductListAll();
                //取消置顶或置尾后 排序采用随机数规则
                Random random = new Random();
                dcProduct.setSort(random.nextInt(count));
                dcProduct.setHotSort(String.valueOf(random.nextInt(count)));
                productDao.updateProduct(dcProduct);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("取消置顶或置尾商品");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "取消置顶或置尾商品失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "取消置顶或置尾商品失败", "");
        }
    }

    @Override
    public CallBackBean exportProductList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = productDao.exportProductList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean qryProductListAll(String json) {
        List<DcProduct> dcProducts = productDao.qryProductListAll();
        return this.joinformateJson(json, "success", dcProducts);
    }

    @Override
    public CallBackBean qryProductCompanyAll(String json) {
        List<Record> record = productDao.qryProductCompanyAll();
        return this.joinformateJson(json, "success", record);
    }
}
