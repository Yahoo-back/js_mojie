package com.hyy.ifm.config.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 9:43
 * @Description Created with IntelliJ IDEA.
 */
public interface ConfigDictService {

    /**
     * 根据data_type查询字段表
     * @param json
     * @return
     */
    public CallBackBean qryDictByDataType(String json);

    /**
     * 列表
     * @param json
     * @return
     */
    public CallBackBean qryDictConfigList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportDictConfigList(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveConfigDict(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveConfig(String json);

    /**
     * 根据id查询
     * @param json
     * @return
     */
    public CallBackBean qryDictById(String json);

    /**
     * 根据id查询
     * @param json
     * @return
     */
    public CallBackBean qryDictByShow(String json);

    /**
     * 更新
     * @param json
     * @return
     */
    public CallBackBean updateConfigDict(String json);

}
