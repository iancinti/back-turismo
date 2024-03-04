package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.application.port.out.DeleteSellerRepository
import com.brainycorp.tourism.util.FileReader.Companion.DELETE_SELLER
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeleteSellerMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteSellerRepository {


    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_SELLER, id)
    }
}