package com.hyy.ifm.data.dao;

import com.hyy.ifm.customer.pojo.QdaoSl;
import com.hyy.ifm.data.pojo.Cnds;
import org.nutz.dao.entity.Record;

import java.util.List;

/**
 * @Author 毛椅俊
 * @Date 2018-03-27 11:52
 * @Description Created with IntelliJ IDEA.
 */
public interface DataSourceDao {

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceList(Cnds cnds);

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceListNoPage(Cnds cnds);



    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryQuDaoSl(Cnds cnds, String source);

    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryQuDaoSl(Cnds cnds);
    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryQuDaoSlAllSource(Cnds cnds, String source);


    int counQuDaoSlAllSource(Cnds cnds);



    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryDataSourceListBySource(Cnds cnds,String source);

    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryDataSourceListBySourceNoPage(Cnds cnds,String source);

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceUserAuth(String source,int userAuth,String createTime);

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceSumMoney(String source,String createTime);

    /**
     * 列表
     * @param
     * @return
     */
    List<Record> qryDataSourceListMobile(String source);

    /**
     * 列表
     * @param cnds
     * @return
     */
    List<Record> qryDataSourceSum(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countDataSourceList(Cnds cnds);

    /**
     * 数目
     * @param cnds
     * @return
     */
    int countDataSourceListQd(Cnds cnds,String source);

    /**
     * 导出
     * @param cnds
     * @return
     */
    List<Record> exportDataSourceList(Cnds cnds);

    /**
     * 查询来源
     * @return
     */
    List<Record> qrySourceAll();
    List<Record> qryMySource(String userCode);
    /**
     * 统计图
     * @param cnds
     * @param s
     * @return
     */
    List<Record> qryDataEchartsSourceBySource(Cnds cnds, String s);

    /**
     * 统计注册数和uv数
     * @param cnds
     */
    List<Record> qryRegisterAndUvCount(Cnds cnds );

    List<Record> qryDataRoorList(Cnds cnds , String s,String h);
    List<Record> qryDataRoorList(Cnds cnds,String h );


    List<Record> qryDataSum(String userCode, String startTime, String endTime);

    List<Record> qryDataSum( String startTime, String endTime);

    List<Record> qryDataSumList(Cnds cnds,String userCode, String startTime, String endTime);

    List<Record> qryDataSumList( Cnds cnds,String startTime, String endTime);


    /**
     * 放款数
     * @param
     * @return
     */
    List<Record> qryQuDaoLoan(Cnds cnds,String userCode,String s ,String h );


}
