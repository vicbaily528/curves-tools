package com.curves.tool.services.dto;

import lombok.Data;

/**
 * 项目相关信息
 * @author li.xiangdong
 */
@Data
public class ProjectDTO {
    private String projectName;
    private String projectFileName;
    private String projectPath;
    private String packagePath;
    private String projectAuthor;
    private String projectMessage;
    private String groupId;
}
