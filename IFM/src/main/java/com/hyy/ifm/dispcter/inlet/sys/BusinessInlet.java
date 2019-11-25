package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.business.service.BusinessCustomerService;
import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-09 19:19
 * @Description 商务管理
 */
@IocBean(scope="singleton", singleton=true)
public class BusinessInlet extends Inlet {
    private static final long serialVersionUID = -8031768607377980743L;

    @Inject
    private BusinessCustomerService businessCustomerService;

    @Override
    public void init() {
        /**
         * 商务合作列表
         */
        serviceTreeMap.put("qryBusinessCustomerList", businessCustomerService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportBusinessCustomerList", businessCustomerService);
    }

}
