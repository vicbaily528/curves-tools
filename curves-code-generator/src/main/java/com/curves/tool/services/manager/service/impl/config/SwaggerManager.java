package com.curves.tool.services.manager.service.impl.config;

import com.curves.framework.commons.utils.FreemarkerUtils;
import com.curves.tool.commons.CFileHelper;
import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.manager.GeneratorManager;
import com.curves.tool.services.manager.service.IGeneratorDisplay;
import com.curves.tool.services.manager.service.IGeneratorObserver;

import java.util.HashMap;
import java.util.Map;

public class SwaggerManager implements IGeneratorObserver, IGeneratorDisplay {

    private GeneratorManager generatorManager;

    public SwaggerManager(GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
        generatorManager.registerObserver(this);
    }

    private TableDO tableDO;
    private ProjectDTO projectDTO;
    private DuridDTO duridDTO;
    private DataBaseDTO dataBaseDTO;
    private String filePath;

    @Override
    public void display() {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("project", projectDTO);


            FreemarkerUtils.generateFile("swagger.flt", CFileHelper.getCodeFile(filePath, "SwaggerConfiguration", ".java"), dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化目录结构
     */
    @Override
    public void init() {
        filePath = CFileHelper.getCommonsPath(projectDTO);
    }

        /**
         * 数据封装
         */
    @Override
    public void generator(DataBaseDTO dataBaseDTO, TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        this.dataBaseDTO = dataBaseDTO;
        this.tableDO = tableDO;
        this.projectDTO = projectDTO;
        this.duridDTO = duridDTO;
        init();
        display();
    }
}