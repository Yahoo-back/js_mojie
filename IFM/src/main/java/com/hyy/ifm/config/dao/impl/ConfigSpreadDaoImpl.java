package com.hyy.ifm.config.dao.impl;

import com.hyy.ifm.common.dao.BaseDao;
import com.hyy.ifm.config.dao.ConfigSpreadDao;
import com.hyy.ifm.config.pojo.Cnds;
import com.hyy.ifm.config.pojo.DcConfig;
import com.hyy.ifm.product.pojo.DcProduct;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:17
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(name = "configSpreadDao")
public class ConfigSpreadDaoImpl extends BaseDao implements ConfigSpreadDao {

    @Override
    public List<Record> qrySpreadConfigList(Cnds cnds) {
        Sql sql = dao.sqls().create("config.spread.list.data");
        sql.params().set("pageNum", getPageNum(cnds.getPageNum() + ""));
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public int countSpreadConfigList(Cnds cnds) {
        try {
            Sql sql = dao.sqls().create("config.spread.list.data.count");
            sql.setVar("condition", cnds.getFuzzyCnd());
            return this.queryforCount(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> exportSpreadConfigList(Cnds cnds) {
        Sql sql = dao.sqls().create("export.config.spread.list.data");
        sql.setVar("condition", cnds.getFuzzyCnd());
        return this.queryforlist(sql);
    }

    @Override
    public DcConfig qrySpreadConfigByName(String name, int id, boolean f) {
        Cnd cnd = Cnd.where("name", "=", name).and("status", "<>", "2");
        if(!f) {
            cnd = Cnd.where("name", "=", name).and("id", "<>", id).and("status", "<>", "2");
        }
        return dao.fetch(DcConfig.class, cnd);
    }

    @Override
    public DcConfig qrySpreadConfigByOn() {
        return dao.fetch(DcConfig.class, Cnd.where("position", "=", "0").and("status", "=", "0"));
    }

    @Override
    public void insertConfigSpread(DcConfig dcConfig) {
        dao.insert(dcConfig);
    }

    @Override
    public void updateConfigSpread(DcConfig dcConfig) {
        dao.update(dcConfig);
    }

    @Override
    public DcConfig qrySpreadConfigById(int id) {
        return dao.fetch(DcConfig.class, Cnd.where("id", "=", id));
    }

}
