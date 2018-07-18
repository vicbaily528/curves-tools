package com.curves.tool;

import com.curves.framework.commons.utils.StringUtils;
import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.ColumnDO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.service.IDataBaseService;
import com.curves.tool.services.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 代码生成实现类主类
 *
 * @author vic
 */
@SpringBootApplication
public class CurvesCodeGeneratorApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CurvesCodeGeneratorApplication.class);
        application.setWebEnvironment(false);
        application.run(args);
    }

    @Autowired
    IDataBaseService iDataBaseService;

    @Autowired
    IGeneratorService iGeneratorService;

    @Override
    public void run(String... args) throws Exception {
        this.generatorCode();
    }

    // 数据库名称
    private String dataBaseName = "curves_user";
    private String dataBaseHost = "127.0.0.1";
    private String dataBaseUser = "root";
    private String dataBasePass = "1qaz@WSX";

    // 表名
    private String tableName = "";

    // 项目相关信息
    private String projectName = "curves-demo";
    private String projectPath = "/Users/li.xiangdong/dev-works/javaWorks/github.com/curves-tools/";
    private String packagePath = "com.curves.service.demo";
    private String projectAuthor = "li.xiangdong";
    private String projectMessage = "系统测试";
    private String groupId = "com.curves.demo";


    // 数据库连接池配置
    private String druidName = "curves";
    private String druidPass = "vicbaily528";
    private String allowAddr = "127.0.0.1";
    private String denyAddr = "";


    /**
     * 代码生成开始
     */
    public void generatorCode() {
        List<TableDO> tableNameList = this.selectTableList();
        this.generatorFile(tableNameList);
    }


    /**
     * 获取数据库相关信息
     * @return List<TableDO> 数据库表对象集合
     */
    private List<TableDO> selectTableList() {
        List<TableDO> tableNameList = null;
        tableNameList = iDataBaseService.getTableNameByDataBase(dataBaseName, tableName);

        if (!CollectionUtils.isEmpty(tableNameList)) {
            tableNameList.forEach(tableName -> {
                List<ColumnDO> columnDOList = iDataBaseService.selectTableColumns(dataBaseName, tableName.getTableName());
                tableName.setColumns(columnDOList);
            });
        }
        return tableNameList;
    }

    /**
     * 生产项目文件
     * @param tableNameList 表数据列表
     */
    private void generatorFile(List<TableDO> tableNameList) {
        DataBaseDTO dataBaseDTO = this.initDataBase();
        ProjectDTO projectDTO = this.initProjectDTO();
        DuridDTO duridDTO = this.initDuridDTO();

        tableNameList.forEach(tableDO -> {
            iGeneratorService.generatorCodeFile(tableDO, projectDTO, duridDTO);
        });
//        iGeneratorService.generatorConfigFile(dataBaseDTO, projectDTO, duridDTO);
    }

    private DataBaseDTO initDataBase() {
        DataBaseDTO dataBaseDTO = new DataBaseDTO();
        dataBaseDTO.setDataBaseHost(this.dataBaseHost);
        dataBaseDTO.setDataBaseName(this.dataBaseName);
        dataBaseDTO.setDataBasePass(this.dataBasePass);
        dataBaseDTO.setDataBaseUser(this.dataBaseUser);
        return dataBaseDTO;
    }

    private ProjectDTO initProjectDTO() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectName(this.projectName);
        projectDTO.setProjectPath(this.projectPath);
        projectDTO.setPackagePath(this.packagePath);
        projectDTO.setProjectAuthor(this.projectAuthor);
        projectDTO.setProjectMessage(this.projectMessage);
        projectDTO.setGroupId(this.groupId);

        projectDTO.setProjectFileName(StringUtils.toHump(projectDTO.getProjectName(), true).concat("Application"));
        return projectDTO;
    }

    private DuridDTO initDuridDTO() {
        DuridDTO duridDTO = new DuridDTO();
        duridDTO.setDruidName(this.druidName);
        duridDTO.setDruidPass(this.druidPass);
        duridDTO.setAllowAddr(this.allowAddr);
        duridDTO.setDenyAddr(this.denyAddr);
        return duridDTO;
    }
}