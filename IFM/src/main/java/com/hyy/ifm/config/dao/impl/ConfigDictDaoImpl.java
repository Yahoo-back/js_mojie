package com.hyy.ifm.config.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.config.dao.ConfigDictDao;
import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.product.pojo.DcProduct;
import com.hyy.ifm.util.StringUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:51
 * @Description Created with IntelliJ IDEA.
 */
@IocBean
public class ConfigDictDaoImpl extends BaseDao implements ConfigDictDao {

    @Override
    public List<DcDict> qryDictByDataType(String data_type) {
        return dao.query(DcDict.class, Cnd.where("data_type", "=", data_type).and("is_use", "=", "1").orderBy("xh", "asc"));
    }

    @Override
    public List<DcDict> qryDictByDataShow() {
        return dao.query(DcDict.class, Cnd.where("showese", "=", "1").and("is_use", "=", "1").orderBy("xh", "asc"));
    }

    @Override
    public List<Record> qryDictConfigList(Cnds cnds) {
        Sql sql = dao.sqls().create("config.dict.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        if(StringUtil.isBlank(cnds.getRows().getData_type())) {
            sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type_cnd()));
        } else {
            sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type()));
        }
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countDictConfigList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("config.dict.list.data.count");
            if(StringUtil.isBlank(cnds.getRows().getData_type())) {
                sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type_cnd()));
            } else {
                sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type()));
            }
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportDictConfigList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.config.dict.list.data");
        if(StringUtil.isBlank(cnds.getRows().getData_type())) {
            sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type_cnd()));
        } else {
            sql.params().set("dataType", StringUtil.nvl(cnds.getRows().getData_type()));
        }
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public DcDict qryDictByName(String name, int id, boolean f) {
        Cnd cnd = Cnd.where("item_value", "=", name).and("is_use", "<>", "2");
        if(!f) {
            cnd = Cnd.where("item_value", "=", name).and("id", "<>", id).and("is_use", "<>", "2");
        }
        return dao.fetch(DcDict.class, cnd);
    }

    @Override
    public void insertConfigDict(DcDict dcDict) {
        dao.insert(dcDict);
    }

    @Override
    public void updateConfigDict(DcDict dcDict) {
        dao.updateIgnoreNull(dcDict);
    }

    @Override
    public DcDict qryDictById(String id) {
        return dao.fetch(DcDict.class, Cnd.where("id", "=", id));
    }

}
