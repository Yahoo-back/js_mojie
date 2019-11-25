package com.hyy.ifm.customer.dao;

import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.customer.pojo.*;
import com.hyy.ifm.product.pojo.UserOrder;
import com.hyy.ifm.sys.pojo.IfmUserOperate;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:35
 * @Description Created with IntelliJ IDEA.
 */
public interface RefundDao {

    /**
     * 查询客户退款
     * @param cnds
     * @return
     */
    List<Record> qryRefund(Cnds cnds);



    /**
     * 跟踪
     * @param userOrder
     */
    void updateOrderAuditStatus(UserOrder userOrder);

    /**
     * 查询我的客户退款
     * @param cnds
     * @return
     */
    List<Record> qryMyRefund(Cnds cnds);

    /**
     * 查询客户退款统计
     * @param cnds
     * @return
     */
    int qryMyRefundCount(Cnds cnds);

    /**
     * 插入用户退款截图
     * @param dcUserRefund
     */
    void insertUserRefund(DcUserRefund dcUserRefund);

    /**
     * 根据用户id查询附件
     * @param id
     * @return
     */
    List<DcUserRefund> qryRefundAttachById(int id);

    /**
     * 退款列表
     * @param cnds
     * @return
     */
    List<Record> qryRefundList(Cnds cnds);

    /**
     * 退款列表统计
     * @param cnds
     * @return
     */
    int qryRefundListCount(Cnds cnds);

    /**
     * 退款列表
     * @param cnds
     * @return
     */
    List<Record> listStatistics(Cnds cnds);

    /**
     * 退款列表统计
     * @param cnds
     * @return
     */
    int listStatisticsCount(Cnds cnds);

    /**
     * 邮件退款
     * @param cnds
     * @return
     */
    List<Record> mailRefundList(Cnds cnds);

    /**
     * 邮件退款统计
     * @param cnds
     * @return
     */
    int mailRefundListCount(Cnds cnds);
}
