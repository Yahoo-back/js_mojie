package com.hyy.ifm.customer.dao;

import com.hyy.ifm.customer.pojo.*;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.sys.pojo.IfmLogin;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:35
 * @Description Created with IntelliJ IDEA.
 */
public interface CustomerDao {

    /**
     * 客户列表
     * @param cnds
     * @return
     */
    List<Record> qryCustomerList(Cnds cnds);

    /**
     * 客户列表
     * @param cnds
     * @return
     */
    List<Record> qryCustomerListNone(Cnds cnds);

    /**
     * 客户列表
     * @param cnds
     * @return
     */
    List<Record> qryCustomerMoneyAll(Cnds cnds);

    /**
     * 渠道名
     * @param cnds
     * @return
     */
    Record qrySysNameAllMoney(String source);



    /**
     * 客户列表数目
     * @param cnds
     * @return
     */
    int countCustomerList(Cnds cnds);

    /**
     * 导出客户列表
     * @param cnds
     * @return
     */
    List<Record> exportCustomerList(Cnds cnds);

    /**
     * 根据id查询客户
     * @param id
     * @return
     */
    Record qryCustomerById(int id);

    /**
     * 根据id查询客户
     */
    List<DcUser> qryCustomerByUuid(String uuid);


    /**
     * 根据id查询客户
     */
    List<Qdao> qryQdaoBySource(String source,String visitTime);


    /**
     * 根据用户id查询附件
     * @param id
     * @return
     */
    List<DcUserAttach> qryCustomerAttachById(int id);

    /**
     * 根据用户id查询附件
     * @param id
     * @return
     */
    List<DcUserInfo> qryCustomerInfoById(int id);

    List<DcUser> qryUserById(int id);

    /**
     * 根据用户id查询附件

     * @return
     */
    List<UserOrder> qryUserOrderByStatus();
    /**
     * 根据id查询
     * @param id
     * @return
     */
    DcUser qryCustomerById2(int id);

    /**
     * 修改用户
     * @param dcUser
     */
    void updateCustomer(DcUser dcUser);

    void updateCustomerInfo(DcUserInfo dcUserInfo);


    int countCustomerListNone(Cnds cnds);

    List<Record> qryPayAmt(Cnds cnds, String s);
}
