//package com.example.OrderSystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration //跟 spring 說此 Class 為配置類
//@EnableOpenApi
//@EnableWebMvc
//public class SwaggerConfig {
//    private ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//            .title("Order_System_API")//標題
//            .description("Order_System_API")//說明
//            .termsOfServiceUrl("urn:tos")//服務器條款網址 默認值
//            .contact(new Contact("DEFAULT", "", ""))//聯絡人
//            .license("Apache 2.0")//
//            .version("v3")//版本
//            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.txt")
//            .build();
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(DEFAULT_API_INFO)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.OrderSystem.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//}
