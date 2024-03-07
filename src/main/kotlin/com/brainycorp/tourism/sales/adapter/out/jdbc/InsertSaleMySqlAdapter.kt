package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.application.port.out.CreateSaleRepository
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class InsertSaleMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateSaleRepository {
    val INSERT_SALE: String = getSql("insertSale")
    override fun execute(sale: Sale){
        try {
            jdbcTemplate.update(
                INSERT_SALE,
                sale.numSale,
                sale.paymentMethod
            )
        }catch (e: Throwable){
            println(e.message)

        }
    }

}