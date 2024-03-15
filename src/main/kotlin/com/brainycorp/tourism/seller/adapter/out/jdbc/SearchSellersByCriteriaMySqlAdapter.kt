package com.brainycorp.tourism.seller.adapter.out.jdbc

import com.brainycorp.tourism.shared.criteria.CriteriaToMySqlConverter
import com.brainycorp.tourism.seller.application.port.out.SearchSellerByCriterialRepository
import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.seller.domain.Seller
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSellersByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): SearchSellerByCriterialRepository {

    val log = LoggerFactory.getLogger("SearchSellersByCriteriaMySqlAdapter")

    override fun execute(criteria: Criteria): List<Seller> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "id" to "id",
                "name" to "name",
                "lastname" to "lastname",
                "dni" to "dni",
                "birthday" to "birthday",
                "nationality" to "nationality",
                "cell_phone" to "cell_phone",
                "email" to "email",
                "employees.charge" to "charge",
                "employees.salary" to "salary"
            ),
            "personal_data",
            criteria
        )
        log.info("Se ejecuta query de filtrado de vendedores: $query")
        return jdbcTemplate.query(query) { rs: ResultSet, _: Int ->
            Seller(
                rs.getInt("id").toString(),
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("dni"),
                rs.getString("birthday"),
                rs.getString("nationality"),
                rs.getString("cell_phone"),
                rs.getString("email"),
                rs.getString("charge"),
                rs.getDouble("salary")
            )
        }
    }

}