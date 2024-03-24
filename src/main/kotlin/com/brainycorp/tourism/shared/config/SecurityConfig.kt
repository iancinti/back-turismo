package com.brainycorp.tourism.shared.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig {


    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeHttpRequests {
                authorize(HttpMethod.POST,"/users/register", hasAuthority("ADMIN"))
                authorize(HttpMethod.GET,"/**", hasAuthority("VENDEDOR"))
                authorize(HttpMethod.POST,"/**", hasAuthority("VENDEDOR"))
                authorize(HttpMethod.PATCH,"/**", hasAuthority("VENDEDOR"))
                authorize(HttpMethod.DELETE,"/**", hasAuthority("VENDEDOR"))
                authorize(anyRequest, authenticated)
            }
            httpBasic {  }
        }
        return http.build()
    }

    @Bean
    fun users(dataSource: DataSource): UserDetailsManager {
        val users = JdbcUserDetailsManager(dataSource)
        return users
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}