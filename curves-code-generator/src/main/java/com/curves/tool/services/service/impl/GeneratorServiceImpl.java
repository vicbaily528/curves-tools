package com.curves.tool.services.service.impl;

import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.manager.GeneratorManager;
import com.curves.tool.services.manager.service.impl.config.*;
import com.curves.tool.services.manager.service.impl.file.*;
import com.curves.tool.services.service.IGeneratorService;
import org.springframework.stereotype.Service;

/**
 * 代码生成接口实现类
 * @author vic
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService {



    /**
     * 生产代码文件
     *
     * @param tableDO 数据库文件
     */
    @Override
    public void generatorCodeFile(TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        GeneratorManager generatorManager = new GeneratorManager();

//        new ControllerManager(generatorManager);
//        new IServiceManager(generatorManager);
//        new ServiceManager(generatorManager);
//        new EntityManager(generatorManager);
//        new DtoManager(generatorManager);
//        new QueryManager(generatorManager);
//        new MapperManager(generatorManager);
        new XmlManager(generatorManager);


        generatorManager.notifyObserver(null, tableDO, projectDTO, duridDTO);
    }

    /**
     * 生成配置代码文件
     */
    @Override
    public void generatorConfigFile(DataBaseDTO dataBaseDTO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        GeneratorManager generatorManager = new GeneratorManager();

        new PomManager(generatorManager);
        new ApplicationManager(generatorManager);
        new YMLManagaer(generatorManager);
        new DruidManager(generatorManager);
        new MyBatisManager(generatorManager);
        new SwaggerManager(generatorManager);

        generatorManager.notifyObserver(dataBaseDTO, null, projectDTO, duridDTO);
    }
}
