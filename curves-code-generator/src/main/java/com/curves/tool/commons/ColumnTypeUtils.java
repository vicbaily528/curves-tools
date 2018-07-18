package com.curves.tool.commons;

import java.util.HashMap;
import java.util.Map;

public class ColumnTypeUtils {

    public static Map<String, String> columnsTypeMap = new HashMap<>(10);

    static  {
        if (columnsTypeMap.size() == 0) {
            columnsTypeMap.put("VARCHAR", "String");
            columnsTypeMap.put("CHAR", "String");
            columnsTypeMap.put("BLOB", "byte[]");
            columnsTypeMap.put("TEXT", "String");
            columnsTypeMap.put("INTEGER", "Long");
            columnsTypeMap.put("TINYINT", "Integer");
            columnsTypeMap.put("SMALLINT", "Integer");
            columnsTypeMap.put("MEDIUMINT", "Integer");
            columnsTypeMap.put("BIGINT", "java.math.BigInteger");
            columnsTypeMap.put("FLOAT", "Float");
            columnsTypeMap.put("DOUBLE", "Double");
            columnsTypeMap.put("DECIMAL", "java.math.BigDecimal");
            columnsTypeMap.put("BOOLEAN", "Integer");
            columnsTypeMap.put("DATE", "java.util.Date");
            columnsTypeMap.put("TIME", "java.util.Date");
            columnsTypeMap.put("DATETIME", "java.util.Date");
            columnsTypeMap.put("TIMESTAMP", "java.util.Date");
            columnsTypeMap.put("YEAR", "java.sql.Date");
        }
    }

    public static String getClassType(String columnsType) {
        if (columnsTypeMap.containsKey(columnsType.toUpperCase())) {
            return columnsTypeMap.get(columnsType.toUpperCase());
        }
        return "String";
    }

}
