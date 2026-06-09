package com.alansilva.financas_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.ast.OpNE;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Financas API")
                        .version("1.0")
                        .description("API para controle financeiro pessoal")
                        .contact(new Contact()
                                .name("Alan Silva")
                                .email("alancristiansouza7@.gmail.com")));
    }
}
