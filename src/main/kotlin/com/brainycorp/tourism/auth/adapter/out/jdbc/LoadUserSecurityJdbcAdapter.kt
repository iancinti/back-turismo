package com.brainycorp.tourism.auth.adapter.out.jdbc

import com.brainycorp.tourism.auth.adapter.out.jdbc.model.UserAuth
import com.brainycorp.tourism.shared.FileReader
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class LoadUserSecurityJdbcAdapter(
    val jdbcTemplate: JdbcTemplate
) {

    val SELECT_USER = FileReader.getSql("selectUserByUsername")


    fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("Username is null")
        }

        try {
            val user = jdbcTemplate.queryForObject(SELECT_USER, arrayOf<Any>(username)) {
                    rs: ResultSet, rowNum: Int ->
                UserAuth(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("authority")
                )
            }
            return user?:throw Exception("")
        } catch (e: EmptyResultDataAccessException) {
            throw UsernameNotFoundException("User not found with username: $username", e)
        }

    }
}