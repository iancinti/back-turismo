package com.brainycorp.tourism.sales.adapter.out.jdbc

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.Client
import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse
import com.brainycorp.tourism.shared.criteria.CriteriaToMySqlConverter
import com.brainycorp.tourism.sales.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.sales.domain.Buyer
import com.brainycorp.tourism.sales.domain.PackageSold
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.sales.domain.ServiceSold
import com.brainycorp.tourism.shared.criteria.Criteria
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSalesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate

): SearchSalesByCriteriaRepository {

    val log = LoggerFactory.getLogger("SearchSalesByCriteriaMySqlAdapter")

    override fun execute(criteria: Criteria): List<SaleResponse> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "num_sale" to "num_sale",
                "payment_method" to "payment_method",
                "cost" to "cost",
                "personal_data.name" to "nameClient",
                "personal_data.lastname" to "lastnameClient",
                "personal_data.email" to "emailClient",
            ), "sales"
            , criteria)
        log.info("Se ejecuta query de filtrado de ventas: $query")
        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
            SaleResponse(
                    rs.getInt("num_sale"),
                    rs.getString("payment_method"),
                    Client(
                            rs.getString("nameClient"),
                            rs.getString("lastnameClient"),
                            rs.getString("emailClient")
                    ),
                    rs.getDouble("cost")
            )
        }


    }

}