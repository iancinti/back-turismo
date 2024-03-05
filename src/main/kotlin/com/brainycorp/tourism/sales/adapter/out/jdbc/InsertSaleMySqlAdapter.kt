package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.application.port.out.CreateSaleRepository
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.util.FileReader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class InsertSaleMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateSaleRepository {

    override fun execute(sale: Sale){
        try {
            jdbcTemplate.update(
                FileReader.INSERT_SALE,
                sale.numSale,
                sale.paymentMethod
            )
        }catch (e: Throwable){
            println(e.message)

        }
    }

}