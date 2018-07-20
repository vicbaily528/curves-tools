package com.curves.tool.services.dao;

import com.curves.tool.services.entity.ColumnDO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.service.IDataBaseService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DataBaseDaoTest {

    @Autowired
    IDataBaseService dataBaseService;

    @Test
    public void getTableNameByDataBase() {
        String filePath = new File("/Users/li.xiangdong/dev-works/javaWorks/github.com/curves-tools/curves-demo/src/main/java/com/curves/service/demo/CurvesDemoApplication.java").getAbsolutePath();
    }

    @Test
    public void selectTableColumns() {
        String databaseName = "curves_user";
        String tableName = "account_user";
        List<ColumnDO> columnDOs = dataBaseService.selectTableColumns(databaseName, tableName);
    }

    @Test
    public void mkdir() throws IOException {
        String path = "/Users/li.xiangdong/dev-works/javaWorks/github.com/curves-tools/curves-demo/src/main/java/com/curves/service/demo/";
        FileUtils.forceMkdir(new File(path));
    }
}