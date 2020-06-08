package com.yuntai.web.config;

import config.Swagger2Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yuanyemustang
 * @title
 * @description
 * @Data 20/5/29 下午6:18
 */
@Configuration
@EnableSwagger2
public class MainWebSwagger2Config {

    @Bean
    public Docket gourdHuWeb() {
        return new Docket( DocumentationType.SWAGGER_2)
                .groupName("MainWeb项目接口文档")
                .apiInfo( Swagger2Config.apiInfo())
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.yuntai.web.controller"))
                .paths( PathSelectors.any()).build();
    }

}
