package com.curves.tool.services.dao;

import com.curves.framework.commons.utils.StringUtils;
import com.curves.tool.services.entity.ColumnDO;
import com.curves.tool.services.entity.TableDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作Mapper
 * @author vic
 */
@Repository
public class DataBaseDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过数据库名称查询出所有的表名称
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<String> 表名称列表
     */
    public List<TableDO> getTableNameByDataBase(String dataBaseName, String tableName) {
        StringBuilder builder = new StringBuilder();
        builder.append("select table_name as tableName, table_comment as tableComment from information_schema.tables where table_schema='%s'");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(tableName)) {
            builder.append(" and TABLE_NAME = '%s'");
        }

        RowMapper<TableDO> rm = BeanPropertyRowMapper.newInstance(TableDO.class);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(tableName)) {
            return jdbcTemplate.query(String.format(builder.toString(), dataBaseName, tableName), rm);
        }
        return jdbcTemplate.query(String.format(builder.toString(), dataBaseName), rm);
    }

    /**
     * 查询表结构信息
     * @param dataBaseName 数据库名称
     * @param tableName 表名称
     * @return List<Object> 表字段对象
     */
    public List<ColumnDO> selectTableColumns(String dataBaseName, String tableName) {
        StringBuilder builder = new StringBuilder();
        builder.append("select t.COLUMN_NAME as columnsName, t.DATA_TYPE as columnsType, t.COLUMN_COMMENT as columnsComment, t.COLUMN_KEY as columnKey ");
        builder.append("from information_schema.columns t ");
        builder.append("where table_schema='%s' and table_name='%s'");

        RowMapper<ColumnDO> rm = BeanPropertyRowMapper.newInstance(ColumnDO.class);
        return jdbcTemplate.query(String.format(builder.toString(),dataBaseName,tableName), rm);
    }
}
