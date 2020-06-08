package config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {

    @Bean
    public Docket gourdHuDemo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuntai"))
                .paths(PathSelectors.any()).build();
    }

    public static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("yuntai-spring-cloud")
                .description("yuntai-spring-cloud是以spring-cloud-alibaba为基础并整合一些常用框架的分布式基础开发平台")
                .termsOfServiceUrl("")
                .contact(new Contact("袁野 ", "", "305025818@qq.com"))
                .version("1.0.0").build();
    }


}
