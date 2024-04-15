package in.krishna.expensetrackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT",AUTHORIZATION_HEADER, "header");
    }
    private List<SecurityContext> securityContexts(){
        return Collections.singletonList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }
    private List<SecurityReference> securityReferences(){
        AuthorizationScope authorizationScope =new AuthorizationScope("global", "access everything");
        return Collections.singletonList(new SecurityReference("JWT", new AuthorizationScope[]{authorizationScope}));
    }
    @Bean
    public Docket apiDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Collections.singletonList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Expense Tracker API's",
                "YOu can tracks your expenses",
                "v1",
                "Terms of Service",
                new Contact("Krishna Nand", "", "1india.kng@gmail.com"),
                "License Of API",
                "LicenseUrl",
                Collections.emptyList());
    }

}
