package com.citytk.springbootcitytuike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.citytk.dao")
@ComponentScan(basePackages = {"com.citytk.*"})
public class SpringbootCitytuikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCitytuikeApplication.class, args);
    }
}
