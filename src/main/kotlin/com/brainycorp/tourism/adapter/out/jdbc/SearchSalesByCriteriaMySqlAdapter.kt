package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.domain.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSalesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate

): SearchSalesByCriteriaRepository {


    override fun execute(criteria: Criteria): List<Sale> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "num_sale" to "num_sale",
                "payment_method" to "payment_method",
                "personal_data.name" to "nameClient",
                "personal_data.lastname" to "lastnameClient",
                "personal_data.email" to "emailClient",
                "tourist_package.code" to "codePackage",
                "tourist_package.name" to "namePackage",
                "tourist_package.destination" to "destinationPackage",
                "tourist_package.cost" to "costPackage",
                "tourist_services.code" to "codeService",
                "tourist_services.description" to "descriptionService",
                "tourist_services.cost" to "costService",
                "tourist_services.destination" to "destinationService",
            ), "sales"
            , criteria)

        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
                Sale(
                    rs.getInt("num_sale"),
                    rs.getString("payment_method"),
                    Client(
                        rs.getString("nameClient"),
                        rs.getString("lastnameClient"),
                        null,
                        null,
                        null,
                        null,
                        rs.getString("emailClient")
                    ),
                    Package(
                        rs.getString("codePackage"),
                        rs.getString("namePackage"),
                        rs.getString("destinationPackage"),
                        rs.getDouble("costPackage"),
                        listOf()
                    ),
                    Service(
                        rs.getString("codeService"),
                        "",
                        rs.getString("destinationService"),
                        rs.getString("destinationService"),
                        "",
                        rs.getDouble("costService"),
                    )
                )
        }


    }

}