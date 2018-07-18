package com.curves.tool.services.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 表字段类型
 * @author vic
 */
@Data
public class ColumnDO {
    /**
     * 表字段名称
     */
    private String columnsName;

    /**
     * 表字段属性
     */
    private String columnsType;

    /**
     * 表字段描述
     */
    private String columnsComment;

    /**
     * 表字段主键
     */
    private String columnKey;

    /**
     * 类属性名称
     */
    private String attributeName;

    /**
     * 类属性类型
     */
    private String attributeType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
