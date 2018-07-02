package com.curves.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vic
 */
@SpringBootApplication
public class CurvesCodeGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CurvesCodeGeneratorApplication.class);
        application.setWebEnvironment(false);
        application.run(args);
    }
}
