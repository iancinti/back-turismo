package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.application.port.out.CreateSaleRepository
import com.brainycorp.tourism.domain.Sale
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