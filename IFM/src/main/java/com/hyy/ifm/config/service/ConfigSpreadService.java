package com.hyy.ifm.config.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:13
 * @Description Created with IntelliJ IDEA.
 */
public interface ConfigSpreadService {

    /**
     * 查询推广配置列表
     * @param json
     * @return
     */
    public CallBackBean qrySpreadConfigList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportSpreadConfigList(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveSpreadConfig(String json);

    /**
     * 根据id查询
     * @param json
     * @return
     */
    public CallBackBean qrySpreadConfigById(String json);

    /**
     * 更新
     * @param json
     * @return
     */
    public CallBackBean updateSpreadConfig(String json);

}
