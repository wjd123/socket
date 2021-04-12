package com.wjd.mynote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author wangjd
 */
@SpringBootApplication
@MapperScan(basePackages = "com.wjd.mynote.dao")
public class MynoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MynoteApplication.class, args);
    }

}
