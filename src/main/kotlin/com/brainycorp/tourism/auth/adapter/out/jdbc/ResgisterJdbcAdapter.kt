package com.brainycorp.tourism.auth.adapter.out.jdbc


import com.brainycorp.tourism.auth.application.port.out.ResgisterRepository
import com.brainycorp.tourism.auth.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ResgisterJdbcAdapter(
    val userDetailsService: UserDetailsManager,
    val passwordEncoder: PasswordEncoder
): ResgisterRepository {

    @Transactional
    override fun execute(user: User) {
        try {
            val userDetail = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.username)
                .password(passwordEncoder.encode(user.password))
                .authorities("VENDEDOR")
                .build()
            userDetailsService.createUser(userDetail)
        }catch (error: Exception){
            println(error.message)
        }
    }
}