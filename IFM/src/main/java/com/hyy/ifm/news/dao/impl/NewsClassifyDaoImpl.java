package com.hyy.ifm.news.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.news.dao.NewsClassifyDao;
import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNewsAssociate;
import com.hyy.ifm.news.pojo.DcNewsType;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 15:01
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "newsClassifyDao")
public class NewsClassifyDaoImpl extends BaseDao implements NewsClassifyDao {

    @Override
    public List<DcNewsType> qryNewsClassifyAll(String type) {
        return dao.query(DcNewsType.class, Cnd.where("type", "=", type).and("status", "=", "0"));
    }

    @Override
    public List<Record> qryNewsTypeList(Cnds cnds) {
        Sql sql = dao.sqls().create("news.classify.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        if(StringUtil.isBlank(cnds.getRows().getType())) {
            sql.params().set("type", StringUtil.nvl(cnds.getRows().getType_cnd()));
        } else {
            sql.params().set("type", StringUtil.nvl(cnds.getRows().getType()));
        }
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countNewsTypeList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("news.classify.list.data.count");
            if(StringUtil.isBlank(cnds.getRows().getType())) {
                sql.params().set("type", StringUtil.nvl(cnds.getRows().getType_cnd()));
            } else {
                sql.params().set("type", StringUtil.nvl(cnds.getRows().getType()));
            }
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportNewsTypeList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.news.classify.list.data");
        if(StringUtil.isBlank(cnds.getRows().getType())) {
            sql.params().set("type", StringUtil.nvl(cnds.getRows().getType_cnd()));
        } else {
            sql.params().set("type", StringUtil.nvl(cnds.getRows().getType()));
        }
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public DcNewsType qryClassifyByName(String nvl, int i, boolean f) {
        Cnd cnd = Cnd.where("name", "=", nvl).and("status", "<>", "2");
        if(!f) {
            cnd = Cnd.where("name", "=", nvl).and("id", "<>", i).and("status", "<>", "2");
        }
        return dao.fetch(DcNewsType.class, cnd);
    }

    @Override
    public void insertClassify(DcNewsType dcNewsType) {
        dao.insert(dcNewsType);
    }

    @Override
    public void updateClassify(DcNewsType dcNewsType) {
        dao.updateIgnoreNull(dcNewsType);
    }

    @Override
    public DcNewsType qryClassifyById(int i) {
        return dao.fetch(DcNewsType.class, Cnd.where("id", "=", i));
    }

    @Override
    public void deleteAssociate(int id, String type) {
        dao.clear(DcNewsAssociate.class, Cnd.where("associate_id", "=", id).and("classify", "=", type));
    }

}
