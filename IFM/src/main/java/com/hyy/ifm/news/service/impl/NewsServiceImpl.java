package com.hyy.ifm.news.service.impl;

import com.hyy.ifm.common.pojo.CallBackBean;
import com.hyy.ifm.common.service.BaseService;
import com.hyy.ifm.news.dao.NewsDao;
import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNews;
import com.hyy.ifm.news.pojo.DcNewsAssociate;
import com.hyy.ifm.news.service.NewsService;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 16:34
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "newsService")
public class NewsServiceImpl extends BaseService implements NewsService {

    @Inject
    private NewsDao newsDao;
    @Inject
    private UserDao userDao;

    @Override
    public CallBackBean qryNewsListAll(String json) {
        List<DcNews> dcNewsList =  newsDao.qryNewsListAll();
        return this.joinformateJson(json, "success", dcNewsList);
    }

    @Override
    public CallBackBean qryNewsList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = newsDao.qryNewsList(cnds);
        int count = newsDao.countNewsList(cnds);
        //总点击量
        for(Record record : res) {
            Record record2 = newsDao.qryNewsClickAllById(record.getString("id"));
            Record record1 = newsDao.qryNewsClickSevenById(record.getString("id"));
            record.set("clickAll", record2.getString("count"));
            record.set("clickSeven", record1.getString("count"));

            if(StringUtil.isBlank(record1.getString("count"))) {
                record.set("clickSeven", "0");
            } else {
                record.set("clickSeven", new DecimalFormat("#,##0").format(Double.parseDouble(record1.getString("count"))));
            }

            if(StringUtil.isBlank(record2.getString("count"))) {
                record.set("clickAll", "0");
            } else {
                record.set("clickAll", new DecimalFormat("#,##0").format(Double.parseDouble(record2.getString("count"))));
            }
        }
        return this.joinformateJson(json, "success", count, res);
    }

    @Override
    public CallBackBean exportNewsList(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        cnds = SqlUtils.apCnd1(cnds);
        List<Record> res = newsDao.exportNewsList(cnds);
        //总点击量
        for(Record record : res) {
            Record record2 = newsDao.qryNewsClickAllById(record.getString("id"));
            Record record1 = newsDao.qryNewsClickSevenById(record.getString("id"));
            record.set("clickAll", record2.getString("count"));
            record.set("clickSeven", record1.getString("count"));

            if(StringUtil.isBlank(record1.getString("count"))) {
                record.set("clickSeven", "0");
            } else {
                record.set("clickSeven", new DecimalFormat("#,##0").format(Double.parseDouble(record1.getString("count"))));
            }

            if(StringUtil.isBlank(record2.getString("count"))) {
                record.set("clickAll", "0");
            } else {
                record.set("clickAll", new DecimalFormat("#,##0").format(Double.parseDouble(record2.getString("count"))));
            }
        }
        return this.joinformateJson(json, "success", res);
    }

    @Override
    public CallBackBean saveNews(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            boolean f = "".equals(cnds.getRows().getId()) ? true : false;
            DcNews temp = newsDao.qryNewsByTitle(StringUtil.nvl(cnds.getRows().getTitle()), StringUtil.parseInt(cnds.getRows().getId()), f);
            if(temp!=null) {
                return this.joinformateJson(json, "该标题已经被使用，请重新输入", "");
            }

            DcNews dcNews = new DcNews();
            dcNews.setTitle(StringUtil.nvl(cnds.getRows().getTitle()));
            dcNews.setIcon(StringUtil.nvl(cnds.getRows().getFile_uri()));
            dcNews.setStatus(StringUtil.nvl(cnds.getRows().getStatus()));
            dcNews.setPosition(StringUtil.nvl(cnds.getRows().getPosition_dd()));
            dcNews.setSort(StringUtil.parseInt(cnds.getRows().getSort()));
            dcNews.setStartTime(DateUtil.StringToDate(StringUtil.nvl(cnds.getRows().getStart_time())));
            dcNews.setEndTime(DateUtil.StringToDate(StringUtil.nvl(cnds.getRows().getEnd_time())));
            dcNews.setUrl(StringUtil.nvl(cnds.getRows().getUrl()));
            dcNews.setContent(StringUtil.nvl(cnds.getRows().getContent()));

            List<DcNewsAssociate> dcNewsAssociates1 = new ArrayList<DcNewsAssociate>();//分类
            List<DcNewsAssociate> dcNewsAssociates2 = new ArrayList<DcNewsAssociate>();;//标签
            List<DcNewsAssociate> dcNewsAssociates3 = new ArrayList<DcNewsAssociate>();//关联商品

            String classify = StringUtil.nvl(cnds.getRows().getClassify());
            if(StringUtil.isNotBlank(classify)) {
                String[] classifyArray = classify.split(",");
                for(String s : classifyArray) {
                    DcNewsAssociate dcNewsAssociate = new DcNewsAssociate();
                    dcNewsAssociate.setClassify("1");
                    dcNewsAssociate.setAssociateId(StringUtil.parseInt(s));
                    dcNewsAssociates1.add(dcNewsAssociate);
                }
            }

            String label = StringUtil.nvl(cnds.getRows().getLabel());
            if(StringUtil.isNotBlank(label)) {
                String[] labelArray = label.split(",");
                for(String s : labelArray) {
                    DcNewsAssociate dcNewsAssociate = new DcNewsAssociate();
                    dcNewsAssociate.setClassify("2");
                    dcNewsAssociate.setAssociateId(StringUtil.parseInt(s));
                    dcNewsAssociates2.add(dcNewsAssociate);
                }
            }

            String productId = StringUtil.nvl(cnds.getRows().getProduct_id());
            if(StringUtil.isNotBlank(productId)) {
                String[] productIdArray = productId.split(",");
                for(String s : productIdArray) {
                    DcNewsAssociate dcNewsAssociate = new DcNewsAssociate();
                    dcNewsAssociate.setClassify("0");
                    dcNewsAssociate.setAssociateId(StringUtil.parseInt(s));
                    dcNewsAssociates3.add(dcNewsAssociate);
                }
            }

            IfmUserOperate ifmUserOperate = new IfmUserOperate();
            ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
            ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));

            if(f) {
                //保存
                dcNews.setCreateTime(new Date());
                newsDao.insertNews(dcNews);
                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates1) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }
                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates2) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }
                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates3) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }

                newsDao.insertNewsAssociate(dcNewsAssociates1);
                newsDao.insertNewsAssociate(dcNewsAssociates2);
                newsDao.insertNewsAssociate(dcNewsAssociates3);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("保存资讯");
                userDao.insertOperate(ifmUserOperate);
            } else {
                //更新
                dcNews.setId(StringUtil.parseInt(cnds.getRows().getId()));
                newsDao.updateNews(dcNews);

                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates1) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }
                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates2) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }
                for(DcNewsAssociate dcNewsAssociate : dcNewsAssociates3) {
                    dcNewsAssociate.setNewsId(dcNews.getId());
                }
                //先删除后插入
                newsDao.deleteNewsAssociateByNewsId(dcNews.getId());
                newsDao.insertNewsAssociate(dcNewsAssociates1);
                newsDao.insertNewsAssociate(dcNewsAssociates2);
                newsDao.insertNewsAssociate(dcNewsAssociates3);

                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("修改资讯");
                userDao.insertOperate(ifmUserOperate);
            }

            return this.joinformateJson(json, "success", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "保存或者更新失败", "");
        }
    }

    @Override
    public CallBackBean qryNewsById(String json) {
        Cnds cnds = Json.fromJson(Cnds.class, json);
        DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(cnds.getRows().getId()));
        List<DcNewsAssociate> dcNewsAssociates = newsDao.qryNewsAssociateByNewsId(StringUtil.parseInt(cnds.getRows().getId()));
        dcNews.setDcNewsAssociates(dcNewsAssociates);
        return this.joinformateJson(json, "success", dcNews);
    }

    @Override
    public CallBackBean updateNews(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);
            DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(cnds.getRows().getId()));

            if(null != dcNews) {
                String status = StringUtil.nvl(cnds.getRows().getStatus());
                if("1".equals(status)) {
                    //下架
                    if("1".equals(dcNews.getStatus())) {
                        return this.joinformateJson(json, "资讯已下线，请刷新后重试", "");
                    }
                    dcNews.setStatus(status);
                } else if("0".equals(status)){
                    //上架
                    if("0".equals(dcNews.getStatus())) {
                        return this.joinformateJson(json, "资讯已发布，请刷新后重试", "");
                    }
                    dcNews.setStatus(status);
                } else if("2".equals(status)){
                    //删除
                    dcNews.setStatus(status);
                }

                newsDao.updateNews(dcNews);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("上下架、删除资讯");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }

            return this.joinformateJson(json, "上下架、删除资讯失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "上下架、删除资讯失败", "");
        }
    }

    @Override
    public CallBackBean topNewsPosition(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(cnds.getRows().getId()));
            if(null != dcNews) {
                dcNews.setPosition("1");
                dcNews.setSort(0);
                newsDao.updateNews(dcNews);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("置顶资讯");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "置顶资讯失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "置顶资讯失败", "");
        }
    }

    @Override
    public CallBackBean cancelNewsPosition(String json) {
        try {
            Cnds cnds = Json.fromJson(Cnds.class, json);

            DcNews dcNews = newsDao.qryNewsById(StringUtil.parseInt(cnds.getRows().getId()));
            if(null != dcNews) {
                dcNews.setPosition("0");
                int count = newsDao.countNewsListAll();
                //取消置顶或置尾后 排序采用随机数规则
                Random random = new Random();
                dcNews.setSort(random.nextInt(count));
                newsDao.updateNews(dcNews);

                IfmUserOperate ifmUserOperate = new IfmUserOperate();
                ifmUserOperate.setIfm_user_id(StringUtil.parseInt(cnds.getUserCode()));
                ifmUserOperate.setAddress(StringUtil.nvl(cnds.getAddress()));
                ifmUserOperate.setOperate_date(new Date());
                ifmUserOperate.setOperate("取消置顶资讯");
                userDao.insertOperate(ifmUserOperate);
                return this.joinformateJson(json, "success", "");
            }
            return this.joinformateJson(json, "取消置顶资讯失败", "");
        } catch (Exception e) {
            e.printStackTrace();
            return this.joinformateJson(json, "取消置顶资讯失败", "");
        }
    }

}
