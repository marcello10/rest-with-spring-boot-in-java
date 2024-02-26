package br.com.marcelo.restwithspringbootinjava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    //Um Bean é um objeto instanciado, gerenciado e montando pelo
    //Spring ioc container
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restful API with java 21 and Spring Boot 3")
                        .version("v1")
                        .description("Alguma descrição sobre a API")
                        .termsOfService("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("")));
    }
}
