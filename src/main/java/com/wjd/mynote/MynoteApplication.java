package com.wjd.mynote;

import com.corundumstudio.socketio.SocketIOServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @author wangjd
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.wjd.mynote.dao")
public class MynoteApplication{

    public static void main(String[] args) {
        SpringApplication.run(MynoteApplication.class, args);
    }

}
