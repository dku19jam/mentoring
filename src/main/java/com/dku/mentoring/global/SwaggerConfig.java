package com.dku.mentoring.global;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "SW융합대학 멘토링 서버 API",
                version = SwaggerConfig.API_VERSION,
                description = "RESTFUL API 제공"
        ),
        servers = {
                @Server(url = "/", description = "로컬 서버")
        }
)
public class SwaggerConfig {
    public static final String API_VERSION = "v1.0.0";
}
