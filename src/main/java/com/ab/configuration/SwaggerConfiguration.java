package com.ab.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import springfox.documentation.service.Contact;

/**
 * @author ab
 * @description swagger configuration
 * @date
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * 扫描所有带@ApiOperation注解的类
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }


    /**
     * api接口作者相关信息
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("ab", "", "ab146268@163.com");
        ApiInfo apiInfo = new ApiInfoBuilder().license("").title("SSM").description("接口文档").contact(contact).version("3.0").build();
        return apiInfo;
    }
}