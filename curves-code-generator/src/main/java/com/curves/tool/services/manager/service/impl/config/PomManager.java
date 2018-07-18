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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PomManager implements IGeneratorObserver, IGeneratorDisplay {

    private GeneratorManager generatorManager;

    public PomManager(GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
        generatorManager.registerObserver(this);
    }

    private TableDO tableDO;
    private ProjectDTO projectDTO;
    private DuridDTO duridDTO;
    private String filePath;

    @Override
    public void display() {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("project", projectDTO);

            FreemarkerUtils.generateFile("pom.flt", CFileHelper.getCodeFile(filePath, "pom", ".xml"), dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化目录结构
     */
    @Override
    public void init() {
        filePath = CFileHelper.getPOMRoot(projectDTO);
    }

        /**
         * 数据封装
         */
    @Override
    public void generator(DataBaseDTO dataBaseDTO, TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        this.tableDO = tableDO;
        this.projectDTO = projectDTO;
        this.duridDTO = duridDTO;
        init();
        display();
    }
}