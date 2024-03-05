package com.brainycorp.tourism.seller.adapter.out.jdbc

import com.brainycorp.tourism.util.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.seller.application.port.out.SearchSellerByCriterialRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.seller.domain.Seller
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSellersByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): SearchSellerByCriterialRepository {


    override fun execute(criteria: Criteria): List<Seller> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
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
        return jdbcTemplate.query(query) { rs: ResultSet, _: Int ->
            Seller(
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