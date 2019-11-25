package com.hyy.ifm.sys.dao;

import com.hyy.ifm.sys.pojo.IfmMuen;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * 菜单项接口
 * 
 * @author Administrator
 *
 */
public interface MeunDao {

    /**
     * 根据用户查询菜单
     * @param userCode
     * @return
     */
	public List<Record> qryMuenByUserid(String userCode);

    /**
     * 查询所有菜单
     * @return
     */
    List<IfmMuen> qryMeuns();
}
