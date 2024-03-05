package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.application.port.`in`.DeleteSaleCommand
import com.brainycorp.tourism.shared.FileReader.Companion.DELETE_SALE
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


@Component
class DeleteSaleMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteSaleCommand {

    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_SALE, id)
    }


}