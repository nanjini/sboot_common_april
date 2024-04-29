package com.sboot.backend.common.core.utils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo(springdocVersion));
    }

    private Info apiInfo(String springdocVersion) {
        return new Info()
                .title("sboot backend Rest API Guide")
                .description("sboot backend 스터디")
                .version(springdocVersion);
    }
}
