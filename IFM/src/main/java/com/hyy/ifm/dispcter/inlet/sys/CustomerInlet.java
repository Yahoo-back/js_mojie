package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.business.service.BusinessCustomerService;
import com.hyy.ifm.customer.service.CustomerService;
import com.hyy.ifm.dispcter.inlet.Inlet;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-15 10:26
 * @Description 客户管理
 */
@IocBean(scope="singleton", singleton=true)
public class CustomerInlet extends Inlet {

    private static final long serialVersionUID = 1220431526574761569L;

    @Inject
    private CustomerService customerService;

    @Override
    public void init() {

        /**
         * 客户列表
         */
        serviceTreeMap.put("qryCustomerList", customerService);

        /**
         * 客户列表
         */
        serviceTreeMap.put("qryCustomerMoneyAll", customerService);

        /**
         * 客户列表
         */
        serviceTreeMap.put("qryCustomerMoneyAllSys", customerService);

        /**
         * 导出客户列表
         */
        serviceTreeMap.put("exportCustomerList", customerService);

        /**
         * 根据id查询用户
         */
        serviceTreeMap.put("qryCustomerById", customerService);
        /**
         * 根据id修改用户详情
         */
        serviceTreeMap.put("updateCustomerById", customerService);

        /**
         * 更新用户
         */
        serviceTreeMap.put("updateCustomer", customerService);
    }

}
