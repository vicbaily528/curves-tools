package com.curves.tool.services.service;

import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;

/**
 * 代码生成接口
 * @author vic
 */
public interface IGeneratorService {

    /**
     * 生产代码文件
     * @param tableDO
     * @param projectDTO
     * @param duridDTO
     */
    void generatorCodeFile(TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO);

    /**
     * 生成配置代码文件
     * @param projectDTO
     */
    void generatorConfigFile(DataBaseDTO dataBaseDTO, ProjectDTO projectDTO, DuridDTO duridDTO);

}
