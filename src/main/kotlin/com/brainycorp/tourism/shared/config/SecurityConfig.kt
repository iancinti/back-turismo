package com.brainycorp.tourism.shared.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke


@Configuration
@EnableWebSecurity
class SecurityConfig {


    @Bean
    open fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            securityMatcher("/**")
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            httpBasic { }
        }
        return http.build()
    }

}