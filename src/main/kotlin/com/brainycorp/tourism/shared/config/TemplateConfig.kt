package com.brainycorp.tourism.shared.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@Configuration
class TemplateConfig {
    @Bean
    fun templateResolver(): ClassLoaderTemplateResolver {
        val pdfTemplateResolver = ClassLoaderTemplateResolver()
        pdfTemplateResolver.prefix = "template/"
        pdfTemplateResolver.suffix = ".html"
        pdfTemplateResolver.templateMode = TemplateMode.HTML
        pdfTemplateResolver.characterEncoding = "UTF-8"
        pdfTemplateResolver.order = 1
        return pdfTemplateResolver
    }

    @Bean
    fun templateEngine(): SpringTemplateEngine? {
        val engine = SpringTemplateEngine()
        engine.setTemplateResolver(templateResolver())
        return engine
    }
}
