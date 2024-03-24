package com.brainycorp.tourism.shared.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
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
                authorize(HttpMethod.POST,"/users/login", permitAll)
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
    fun authenticationManager(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
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