package com.ldnhat.stdiomanagement.config;

import com.ldnhat.stdiomanagement.common.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private ApiKey apiKey(){
        return new ApiKey(Constant.JWT,
                Constant.AUTHORIZATION, Constant.HEADER);
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Collections.singletonList(security()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    public SecurityContext security(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new
                AuthorizationScope(Constant.GLOBAL, Constant.ACCESS_EVERYTHING);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference(Constant.JWT,
                authorizationScopes));
    }
}
