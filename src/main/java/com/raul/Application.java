package com.raul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Raul on 1/30/17.
 */
@EnableSwagger2
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }



    @Bean
    public Docket SubscriptionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("subscription-api")
                .apiInfo(SubscriptionapiInfo())
                .select()
                .paths(regex("/subscription.*"))
                .build();

    }


    private ApiInfo SubscriptionapiInfo() {
        return new ApiInfoBuilder()
                .title(" MicroService with Swagger")
                .description("Create your Subscription here")
                .termsOfServiceUrl("http://raul.com")
                .contact("Raul Lavin")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.1")
                .build();
    }

}

