package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.application.port.out.SearchSellerByCriterialRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Seller
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchSellersByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): SearchSellerByCriterialRepository {


    override fun execute(criteria: Criteria): List<Seller> {
        val query = CriteriaToMySqlConverter.convert(
            listOf("name", "lastname", "dni", "birthday", "nationality", "cell_phone", "email", "employees.charge", "employees.salary"),
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
                rs.getString("employees.charge"),
                rs.getDouble("employees.salary")
            )
        }
    }

}