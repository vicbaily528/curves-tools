package com.curves.tool.services.manager;

import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.manager.service.IGeneratorObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

@Component
public class GeneratorManager implements IGenerator{

    private static final int initialCapacity = 10;

    public List<IGeneratorObserver> observers;

    public GeneratorManager() {
        observers = new ArrayList<>(initialCapacity);
    }

    @Override
    public void registerObserver(IGeneratorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void remogeObserver(IGeneratorObserver observer) {
        int index = observers.indexOf(observer);
        if (index > 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver(DataBaseDTO dataBaseDTO, TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        for(IGeneratorObserver observer : observers) {
            observer.generator(dataBaseDTO, tableDO, projectDTO, duridDTO);
        }
    }
}
