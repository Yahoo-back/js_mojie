package com.hyy.ifm.news;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 14:59
 * @Description Created with IntelliJ IDEA.
 */
public interface NewsClassifyService {

    /**
     * 查询所有新闻类别
     * @param json
     * @return
     */
    public CallBackBean qryNewsClassifyAll(String json);

    /**
     * 查询列表
     * @param json
     * @return
     */
    public CallBackBean qryNewsTypeList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportNewsTypeList(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveClassify(String json);

    /**
     * 根据id查询
     * @param json
     * @return
     */
    public CallBackBean qryClassifyById(String json);

    /**
     * 更新
     * @param json
     * @return
     */
    public CallBackBean updateClassify(String json);

}

