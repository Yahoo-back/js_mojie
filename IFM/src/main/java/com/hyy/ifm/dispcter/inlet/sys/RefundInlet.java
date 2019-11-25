package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.customer.service.RefundService;
import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;


@IocBean(scope="singleton", singleton=true)
public class RefundInlet extends Inlet {

    private static final long serialVersionUID = 1220431526574761569L;

    @Inject
    private RefundService refundService;

    @Override
    public void init() {

        /**
         * 退款客户
         */
        serviceTreeMap.put("qryRefund", refundService);

        /**
         * 跟踪
         */
        serviceTreeMap.put("updateOrderAuditStatus", refundService);

        /**
         * 我的退款跟踪
         */
        serviceTreeMap.put("qryMyRefund", refundService);

        /**
         * 保存退款操作
         */
        serviceTreeMap.put("saveRefund", refundService);

        /**
         * 根据id查询用户
         */
        serviceTreeMap.put("qryRefundAttachById", refundService);

        /**
         * 客户退款列表
         */
        serviceTreeMap.put("qryRefundList", refundService);

        /**
         * 客户退款列表
         */
        serviceTreeMap.put("listStatistics", refundService);

        /**
         * 邮件退款列表
         */
        serviceTreeMap.put("mailRefundList", refundService);
    }

}
