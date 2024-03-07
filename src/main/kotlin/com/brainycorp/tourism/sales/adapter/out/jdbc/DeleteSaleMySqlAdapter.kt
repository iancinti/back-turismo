package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.application.port.`in`.DeleteSaleCommand
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


@Component
class DeleteSaleMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteSaleCommand {
    val DELETE_SALE: String = getSql("deleteSale")
    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_SALE, id)
    }


}