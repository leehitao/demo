package com.xrt.bzj.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.xrt.bzj"})
@MapperScan("com.xrt.bzj.dao.mapper")
@EnableSwagger2
@Slf4j
public class BzjWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BzjWebApplication.class, args);
        print(context);
    }

    /**
     * 启动成功之后，打印项目信息
     */
    public static void print(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();

        // 项目端口
        String port = environment.getProperty("server.port");
        log.info("serverIp : {}", "127.0.0.1");
        log.info("port : {}", port);
        String swaggerUrl = "http://127.0.0.1" + ":" + port + "/swagger-ui.html";
        log.info("Swagger: {}", swaggerUrl);
        log.info("project start success...........");
    }
}
