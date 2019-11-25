package com.hyy.ifm.product.dao;



import com.hyy.ifm.product.pojo.UserOrder;

import java.util.List;
import java.util.Map;

/**
 * 自动发布产品编号Dao
 * 
 * @author zyp
 * @version 1.0.0
 * @date 2019-07-02 11:47:21
 * Copyright 江苏芒种互联信息科技有限公司  arc All Rights Reserved
 * 官方网站：www.graininear.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserOrderDao {

    void updateSelective(UserOrder userOrder);

    UserOrder findByPrimary(Integer id);





    

}
