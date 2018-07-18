package com.curves.tool.services.service.impl;

import com.curves.framework.commons.utils.StringUtils;
import com.curves.tool.commons.ColumnTypeUtils;
import com.curves.tool.services.dao.DataBaseDao;
import com.curves.tool.services.entity.ColumnDO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.service.IDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作接口实现类
 * @author vic
 */
@Service
public class DataBaseServiceImpl implements IDataBaseService {

    @Autowired
    DataBaseDao dataBaseDao;


    /**
     * 通过数据库名称查询出所有的表名称
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<String> 表名称列表
     */
    @Override
    public List<TableDO> getTableNameByDataBase(String dataBaseName, String tableName) {
        List<TableDO> tables = dataBaseDao.getTableNameByDataBase(dataBaseName, tableName);
        tables.forEach(tableDO -> {
            tableDO.setClassName(StringUtils.toHump(tableDO.getTableName(), true));
            tableDO.setAttributeName(StringUtils.toHump(tableDO.getTableName(), false));
        });
        return tables;
    }

    /**
     * 查询表结构信息
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<Object> 表字段对象
     */
    @Override
    public List<ColumnDO> selectTableColumns(String dataBaseName, String tableName) {
        List<ColumnDO> columnDOList = dataBaseDao.selectTableColumns(dataBaseName, tableName);
        columnDOList.forEach(column -> {
            column.setAttributeName(StringUtils.toHump(column.getColumnsName(), false));
            column.setAttributeType(ColumnTypeUtils.getClassType(column.getColumnsType()));
        });
        return columnDOList;
    }
}
