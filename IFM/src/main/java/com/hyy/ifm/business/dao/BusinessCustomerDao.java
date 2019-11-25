package com.hyy.ifm.business.dao;

import com.hyy.ifm.business.pojo.Cnds;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 19:25
 * @Description Created with IntelliJ IDEA.
 */
public interface BusinessCustomerDao {

    /**
     * 商务合作列表
     * @param cnds
     * @return
     */
    List<Record> qryBusinessCustomerList(Cnds cnds);

    /**
     * 商务合作数目
     * @param cnds
     * @return
     */
    int countBusinessCustomerList(Cnds cnds);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportBusinessCustomerList(Cnds cnds);
}
