package com.curves.tool.services.entity;

import lombok.Data;

/**
 * @author vic
 */
@Data
public class ColumnDO {
    private String dbName;
    private String dbType;
    private String attributeName;
    private String attributeType;
}
