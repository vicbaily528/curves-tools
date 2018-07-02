package com.curves.tool.services.service.impl;

import com.curves.tool.services.dao.DataBaseDao;
import com.curves.tool.services.service.IDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据库操作接口实现类
 * @author vic
 */
@Service
public class DataBaseServiceImpl implements IDataBaseService {

    @Autowired
    DataBaseDao dataBaseDao;
}
