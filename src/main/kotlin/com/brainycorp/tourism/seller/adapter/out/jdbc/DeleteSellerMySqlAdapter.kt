package com.brainycorp.tourism.seller.adapter.out.jdbc

import com.brainycorp.tourism.seller.application.port.out.DeleteSellerRepository
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeleteSellerMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteSellerRepository {
    val DELETE_SELLER: String = getSql("deleteSeller")

    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_SELLER, id)
    }
}