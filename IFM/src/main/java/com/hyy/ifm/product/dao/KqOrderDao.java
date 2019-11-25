package com.hyy.ifm.product.dao;



import com.hyy.ifm.product.pojo.KqOrder;

import java.util.Map;

public interface KqOrderDao {


    void insertSelective(KqOrder record);



    KqOrder selectLastByUserIdAndTypeAndOuterId(Map<String, Object> param);

}
