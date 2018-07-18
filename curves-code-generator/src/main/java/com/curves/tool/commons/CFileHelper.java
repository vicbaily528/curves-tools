package com.curves.tool.commons;

import com.curves.tool.services.dto.ProjectDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class CFileHelper {

    public static final String src = "/src/main/java/";
    public static final String resource = "/src/main/resources/";

    /**
     * 获取项目src业务根目录 <br/>
     * 带主包路径
     * @param projectDTO 项目信息
     * @return String src路径
     */
    public static String getSrcRoot(ProjectDTO projectDTO) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(projectDTO.getProjectPath());
        buffer.append(projectDTO.getProjectName());
        buffer.append(src);
        buffer.append(projectDTO.getPackagePath().replaceAll("\\.","/"));
        return buffer.append("/").toString();
    }

    /**
     * 获取Commons包路径
     * @param projectDTO
     * @return
     */
    public static String getCommonsPath(ProjectDTO projectDTO) {
        return getSrcRoot(projectDTO).concat("commons/");
    }

    /**
     * 获取业务包路径
     * @param projectDTO
     * @param packageType
     * @return
     */
    public static String getServicesPath(ProjectDTO projectDTO, String packageType) {
        return getSrcRoot(projectDTO).concat("services/").concat(packageType).concat("/");
    }

    /**
     * 获取Mybatis XML文件路径
     * @param projectDTO
     * @return
     */
    public static String getMyBatisMapperPath(ProjectDTO projectDTO) {
        return getResourceRoot(projectDTO).concat("mapper/");
    }

    /**
     * 获取资源根目录
     * @param projectDTO 项目信息
     * @return String src路径
     */
    public static String getResourceRoot(ProjectDTO projectDTO) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(projectDTO.getProjectPath());
        buffer.append(projectDTO.getProjectName());
        buffer.append(resource);
        return buffer.append("/").toString();
    }

    /**
     * 获取POM根目录
     * @param projectDTO 项目信息
     * @return String src路径
     */
    public static String getPOMRoot(ProjectDTO projectDTO) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(projectDTO.getProjectPath());
        buffer.append(projectDTO.getProjectName());
        return buffer.append("/").toString();
    }

    /**
     * 创建文件夹
     * @param filePath 文件夹路径
     */
    public static void create(String filePath) {
        try {
            if (StringUtils.isNotBlank(filePath)) {
                FileUtils.forceMkdir(new File(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File getCodeFile(String path, String fileName, String tix) {
        StringBuilder builder = new StringBuilder();
        builder.append(path);
        builder.append(fileName);
        builder.append(tix);
        System.out.println(builder.toString());
        return new File(builder.toString());
    }

}
