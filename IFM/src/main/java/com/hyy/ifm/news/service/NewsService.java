package com.hyy.ifm.news.service;

import com.hyy.ifm.common.pojo.CallBackBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 16:33
 * @Description Created with IntelliJ IDEA.
 */
public interface NewsService {

    /**
     * 查询新闻列表（上架）
     * @param json
     * @return
     */
    public CallBackBean qryNewsListAll(String json);

    /**
     * 查询列表
     * @param json
     * @return
     */
    public CallBackBean qryNewsList(String json);

    /**
     * 导出
     * @param json
     * @return
     */
    public CallBackBean exportNewsList(String json);

    /**
     * 保存
     * @param json
     * @return
     */
    public CallBackBean saveNews(String json);

    /**
     * 根据id查询消息
     * @param json
     * @return
     */
    public CallBackBean qryNewsById(String json);

    /**
     * 更新资讯
     * @param json
     * @return
     */
    public CallBackBean updateNews(String json);

    /**
     * 置顶
     * @param json
     * @return
     */
    public CallBackBean topNewsPosition(String json);

    /**
     * 取消置顶
     * @param json
     * @return
     */
    public CallBackBean cancelNewsPosition(String json);

}
