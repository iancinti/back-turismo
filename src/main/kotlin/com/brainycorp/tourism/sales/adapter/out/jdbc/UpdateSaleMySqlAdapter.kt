package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.out.UpdateSaleRepository
import com.brainycorp.tourism.util.FileReader.Companion.UPDATE_SALE
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UpdateSaleMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdateSaleRepository {

    override fun execute(sale: SaleRequest, saleId: String) {

        try {

            jdbcTemplate.update(
                UPDATE_SALE,
                sale.paymentMethod,
                sale.client,
                sale.packagee,
                sale.service,
                saleId
            )
        }catch (error: DataAccessException){
            println(error)
        }

    }

}