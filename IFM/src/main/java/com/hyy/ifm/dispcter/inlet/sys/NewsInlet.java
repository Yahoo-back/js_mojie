package com.hyy.ifm.dispcter.inlet.sys;

import com.hyy.ifm.dispcter.inlet.Inlet;
import com.hyy.ifm.news.NewsClassifyService;
import com.hyy.ifm.news.service.NewsService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 16:31
 * @Description Created with IntelliJ IDEA.
 */
@IocBean(scope="singleton", singleton=true)
public class NewsInlet extends Inlet {

    private static final long serialVersionUID = -2974982812596114252L;

    @Inject
    private NewsService newsService;
    @Inject
    private NewsClassifyService newsClassifyService;

    @Override
    public void init() {
        /**
         * 查询新闻列表（上架）
         */
        serviceTreeMap.put("qryNewsListAll", newsService);

        /**
         * 列表
         */
        serviceTreeMap.put("qryNewsList", newsService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportNewsList", newsService);

        /**
         * 查询所有新闻类别
         */
        serviceTreeMap.put("qryNewsClassifyAll", newsClassifyService);

        /**
         * 保存
         */
        serviceTreeMap.put("saveNews", newsService);

        /**
         * 根据id查询消息
         */
        serviceTreeMap.put("qryNewsById", newsService);

        /**
         * 更新资讯
         */
        serviceTreeMap.put("updateNews", newsService);

        /**
         * 置顶
         */
        serviceTreeMap.put("topNewsPosition", newsService);

        /**
         * 取消置顶
         */
        serviceTreeMap.put("cancelNewsPosition", newsService);

        /**
         * 查询列表
         */
        serviceTreeMap.put("qryNewsTypeList", newsClassifyService);

        /**
         * 导出
         */
        serviceTreeMap.put("exportNewsTypeList", newsClassifyService);

        /**
         * 保存
         */
        serviceTreeMap.put("saveClassify", newsClassifyService);

        /**
         * 根据id查询
         */
        serviceTreeMap.put("qryClassifyById", newsClassifyService);

        /**
         * 更新
         */
        serviceTreeMap.put("updateClassify", newsClassifyService);
    }

}
