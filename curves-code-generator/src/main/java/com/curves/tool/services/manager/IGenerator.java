package com.curves.tool.services.manager;

import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.manager.service.IGeneratorObserver;

import java.util.Observer;

public interface IGenerator {
    void registerObserver(IGeneratorObserver observer);
    void remogeObserver(IGeneratorObserver observer);
    void notifyObserver(DataBaseDTO dataBaseDTO, TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO);
}
