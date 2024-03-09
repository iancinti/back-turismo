package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.out.CreateSaleRepository
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class InsertSaleMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateSaleRepository {
    val INSERT_SALE: String = getSql("insertSale")
    override fun execute(sale: SaleRequest){
        try {
            jdbcTemplate.update(
                INSERT_SALE,
                sale.numSale,
                sale.paymentMethod,
                sale.client,
                sale.packagee,
                sale.service
            )
        } catch (e: DataIntegrityViolationException) {
            println("Error de integridad de datos: ${e.cause?.message}")
        }catch (e: Throwable){
            println(e.message)
        }
    }

}