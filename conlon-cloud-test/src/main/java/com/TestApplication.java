package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dao")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class , args);
    }
}
