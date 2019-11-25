package com.hyy.ifm.news.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.news.NewsClassifyService;
import com.hyy.ifm.news.dao.NewsClassifyDao;
import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNewsType;
import com.hyy.ifm.sys.dao.UserDao;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import com.hyy.ifm.sys.service.impl.SqlUtils;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 15:00
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "newsClassifySerivce")
public class NewsClassifyServiceImpl extends BaseService implements NewsClassifyService {

    @Inject
    private NewsClassifyDao newsClassifyDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryNewsClassifyAll(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        List<DcNewsType> dcNewsTypes =  newsClassifyDao.qryNewsClassifyAll(StringUtil.nvl(cnds.getRows().getType()));
        return this.joinformateJson(json, "success", dcNewsTypes);
    }

    @Override
    public CallBackBean qryNewsTypeList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = newsClassifyDao.qryNewsTypeList(cnds);
        int count = newsClassifyDao.countNewsTypeList(cnds);
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportNewsTypeList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd2(cnds);
        List<Record> res = newsClassifyDao.exportNewsTypeList(cnds);
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean saveClassify(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;
            DcNewsType temp = newsClassifyDao.qryClassifyByName(StringUtil.nvl(cnds.getRows().getName()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(null != temp) {
                return this.joinformateJson(json, "该名称已经被使用，请重新输入", "");
            }

            DcNewsType dcNewsType = new DcNewsType();
            dcNewsType.setName(StringUtil.nvl(cnds.getRows().getName()));
            dcNewsType.setType(StringUtil.nvl(cnds.getRows().getType()));
            dcNewsType.setSort(StringUtil.nvl(cnds.getRows().getSort()));
            dcNewsType.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcNewsType.setCreateTime(new Date());
                newsClassifyDao.insertClassify(dcNewsType);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存资讯配置");
                userDao.insertOperate(ifmUserOperate);

            } else {
                //更新
                dcNewsType.setId(StringUtil.parseInt(cnds.getRows().getId()));
                newsClassifyDao.updateClassify(dcNewsType);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改资讯配置");
                userDao.insertOperate(ifmUserOperate);
            }
            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryClassifyById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcNewsType dcNewsType = newsClassifyDao.qryClassifyById(StringUtil.parseInt(cnds.getRows().getId()));
        return this.joinformateJson(json, "success", dcNewsType);
    }

    @Override
    public CallBackBean updateClassify(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcNewsType dcNewsType = newsClassifyDao.qryClassifyById(StringUtil.parseInteger(cnds.getRows().getId()));
            String type = StringUtil.nvl(cnds.getRows().getType());

            if(null != dcNewsType) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if("1".equals(status)) {
                    //下架
                    if("1".equals(dcNewsType.getStatus())) {
                        return this.joinformateJson(json, "已下架，请刷新后重试", "");
                    }
                    dcNewsType.setStatus(status);

                    //删除关联信息
                    newsClassifyDao.deleteAssociate(dcNewsType.getId(), type);
                } else if("0".equals(status)){
                    //上架
                    if("0".equals(dcNewsType.getStatus())) {
                        return this.joinformateJson(json, "已上架，请刷新后重试", "");
                    }
                    dcNewsType.setStatus(status);
                } else if("2".equals(status)){
                    //删除
                    dcNewsType.setStatus(status);

                    //删除关联信息
                    newsClassifyDao.deleteAssociate(dcNewsType.getId(), type);
                }

                newsClassifyDao.updateClassify(dcNewsType);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、删除资讯配置");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "上下架、删除资讯配置失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "上下架、删除资讯配置失败", "");
        }
    }

}
