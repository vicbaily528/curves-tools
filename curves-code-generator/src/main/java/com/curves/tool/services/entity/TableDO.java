package com.curves.tool.services.entity;

import lombok.Data;

import java.util.List;

/**
 * @author vic
 */
@Data
public class TableDO {
    private String dbName;
    private String className;
    private String attributeName;
    private List<ColumnDO> columns;
}
