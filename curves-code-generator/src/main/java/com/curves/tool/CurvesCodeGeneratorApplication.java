package com.curves.tool;

import com.curves.tool.services.service.IDataBaseService;
import com.curves.tool.services.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成实现类主类
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

    }
}
