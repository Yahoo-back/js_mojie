package com.hyy.ifm.news.dao;

import com.hyy.ifm.news.pojo.Cnds;
import com.hyy.ifm.news.pojo.DcNews;
import com.hyy.ifm.news.pojo.DcNewsAssociate;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-26 15:31
 * @Description Created with IntelliJ IDEA.
 */
public interface NewsDao {

    /**
     * 根据id查询新闻
     * @param id
     * @return
     */
    DcNews qryNewsById(int id);

    /**
     * 查询新闻列表（上架）
     * @return
     */
    List<DcNews> qryNewsListAll();

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryNewsList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countNewsList(Cnds cnds);

    /**
     * 总点击量
     * @param id
     * @return
     */
    Record qryNewsClickAllById(String id);

    /**
     * 七天点击量
     * @param id
     * @return
     */
    Record qryNewsClickSevenById(String id);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportNewsList(Cnds cnds);

    /**
     * 根据标题查询
     * @param name
     * @param id
     * @param f
     * @return
     */
    DcNews qryNewsByTitle(String name, int id, boolean f);

    /**
     * 保存
     * @param dcNews
     */
    void insertNews(DcNews dcNews);

    /**
     * 修改
     * @param dcNews
     */
    void updateNews(DcNews dcNews);

    /**
     * 保存关联
     * @param dcNewsAssociates1
     */
    void insertNewsAssociate(List<DcNewsAssociate> dcNewsAssociates1);

    /**
     * 根据newsid删除新闻关联
     * @param id
     */
    void deleteNewsAssociateByNewsId(int id);

    /**
     * 根据id查询关联信息
     * @param i
     * @return
     */
    List<DcNewsAssociate> qryNewsAssociateByNewsId(int i);

    /**
     * 查询多少个
     * @return
     */
    int countNewsListAll();
}
