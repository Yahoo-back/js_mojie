package com.hyy.ifm.product.dao;



import com.hyy.ifm.product.pojo.CjOrder;
import com.hyy.ifm.product.pojo.KqOrder;

import java.util.Map;

public interface CjOrderDao {


    void insertSelective(CjOrder record);



    CjOrder selectLastByUserIdAndTypeAndOuterId(Map<String, Object> param);

}
