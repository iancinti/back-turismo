package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Sale
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSalesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate

): SearchSalesByCriteriaRepository {


    override fun execute(criteria: Criteria): List<Sale> {
        val query = CriteriaToMySqlConverter.convert(listOf("num_sale", "payment_method"), "sales", criteria)

        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
                Sale(
                    rs.getInt("num_sale"),
                    rs.getString("payment_method")
                )
        }


    }

}