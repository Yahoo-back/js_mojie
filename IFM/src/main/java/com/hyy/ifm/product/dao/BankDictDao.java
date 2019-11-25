package com.hyy.ifm.product.dao;




import com.hyy.ifm.config.pojo.DcDict;
import com.hyy.ifm.product.pojo.XfOrder;
import org.nutz.dao.entity.Record;


public interface BankDictDao {



    Record selectByItemValue(String bankOpen);
}
