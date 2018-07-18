package com.curves.tool.services.service;

import com.curves.tool.services.entity.ColumnDO;
import com.curves.tool.services.entity.TableDO;

import java.util.List;

/**
 * 数据库操作接口
 * @author vic
 */
public interface IDataBaseService {

    /**
     * 通过数据库名称查询出所有的表名称
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<String> 表名称列表
     */
    List<TableDO> getTableNameByDataBase(String dataBaseName, String tableName);


    /**
     * 查询表结构信息
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<Object> 表字段对象
     */
    List<ColumnDO> selectTableColumns(String dataBaseName, String tableName);
}
