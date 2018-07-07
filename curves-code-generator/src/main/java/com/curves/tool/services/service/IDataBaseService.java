package com.curves.tool.services.service;

import java.util.List;

/**
 * 数据库操作接口
 * @author vic
 */
public interface IDataBaseService {

    /**
     * 查询表名称
     * @param dataBaseName
     * @return
     */
    List<String> queryDataBaseTableName(String dataBaseName);
}
