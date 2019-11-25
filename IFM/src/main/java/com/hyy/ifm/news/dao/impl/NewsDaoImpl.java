package com.hyy.ifm.news.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.data.pojo.DcProductVisitLog;
import com.hyy.ifm.news.dao.NewsDao;
import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNews;
import com.hyy.ifm.news.pojo.DcNewsAssociate;
import com.hyy.ifm.product.pojo.DcProduct;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:32
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "newsDao")
public class NewsDaoImpl extends BaseDao implements NewsDao {

    @Override
    public DcNews qryNewsById(int id) {
        return dao.fetch(DcNews.class, Cnd.where("id", "=", id));
    }

    @Override
    public List<DcNews> qryNewsListAll() {
        return dao.query(DcNews.class, Cnd.where("status", "=", "0"));
    }

    @Override
    public List<Record> qryNewsList(Cnds cnds) {
        Sql sql = dao.sqls().create("news.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countNewsList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("news.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Record qryNewsClickAllById(String id) {
        Sql sql = dao.sqls().create("news.click.all.data");
        sql.params().set("newId", id);
        return queryforEntity(sql);
    }

    @Override
    public Record qryNewsClickSevenById(String id) {
        Sql sql = dao.sqls().create("news.click.seven.data");
        sql.params().set("newId", id);
        return queryforEntity(sql);
    }

    @Override
    public List<Record> exportNewsList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.news.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public DcNews qryNewsByTitle(String name, int id, boolean f) {
        Cnd cnd = Cnd.where("title", "=", name).and("status", "<>", "2");
        if(!f) {
            cnd = Cnd.where("title", "=", name).and("id", "<>", id).and("status", "<>", "2");
        }
        return dao.fetch(DcNews.class, cnd);
    }

    @Override
    public void insertNews(DcNews dcNews) {
        dao.insert(dcNews);
    }

    @Override
    public void updateNews(DcNews dcNews) {
        dao.update(dcNews);
    }

    @Override
    public void insertNewsAssociate(List<DcNewsAssociate> dcNewsAssociates1) {
        dao.fastInsert(dcNewsAssociates1);
    }

    @Override
    public void deleteNewsAssociateByNewsId(int id) {
        dao.clear(DcNewsAssociate.class, Cnd.where("news_id", "=", id));
    }

    @Override
    public List<DcNewsAssociate> qryNewsAssociateByNewsId(int i) {
        return dao.query(DcNewsAssociate.class, Cnd.where("news_id", "=", i));
    }

    @Override
    public int countNewsListAll() {
        return dao.count(DcNews.class);
    }
}
