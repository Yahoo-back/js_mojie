package com.hyy.ifm.product.dao;

import com.hyy.ifm.product.pojo.UserBankcard;

import java.util.List;
import java.util.Map;

public interface UserBankcardDao {

    UserBankcard selectByUserAndCardAndAuthFrom(Map<String, Object> param);


    UserBankcard selectByUserId(Map<String, Object> param);


}
