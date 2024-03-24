package com.brainycorp.tourism.shared

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun TuristeAROpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("TuristeAR API")
                    .description("Aplicación para administrar una agencia de turismo")
                    .version("v1.0.0")
            )
    }
}