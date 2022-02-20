package com.xrt.bzj.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@ComponentScan({"com.xrt.bzj"})
@MapperScan("com.xrt.bzj.dao.mapper")
@EnableSwagger2
public class BzjWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BzjWebApplication.class, args);
    }

}
