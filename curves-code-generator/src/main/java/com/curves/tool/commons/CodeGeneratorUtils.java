package com.curves.tool.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * @author li.xiangdong
 */
public class CodeGeneratorUtils {

    /**
     *
     * @param packagePath
     * @param sourcePath
     * @return
     */
    public static String packageFilePath(String packagePath, String sourcePath, String fileName) {
        StringBuilder packageFilePath = new StringBuilder();
        packageFilePath.append(packagePath.replaceAll(".","/"));
        if (StringUtils.isNotBlank(sourcePath)) {
            packageFilePath.append(sourcePath);
        }
        if (StringUtils.isNotBlank(fileName)) {
            packageFilePath.append(fileName);
        }
        return packageFilePath.toString();
    }

}
