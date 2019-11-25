package com.hyy.ifm.product.dao;


import com.hyy.ifm.product.pojo.XfCallbackLog;

public interface XfCallbackLogDao {

    /**
     * 根据表名查询表数量
     * @param tableName
     * @return
     */
    int countTable(String tableName);

    /**
     * 根据表名创建表
     * @param tableName
     */
    void createTable(String tableName);

    /**
     * (分表)新增
     * @param tableName
     * @param record
     * @return
     */
    void saveShard(String tableName, XfCallbackLog record);


}