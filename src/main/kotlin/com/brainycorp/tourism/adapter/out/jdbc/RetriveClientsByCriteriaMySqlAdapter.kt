package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.application.port.out.RetriveClientsByCriteriaRepository
import com.brainycorp.tourism.domain.Client
import com.brainycorp.tourism.domain.Criteria
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class RetriveClientsByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): RetriveClientsByCriteriaRepository {
    override fun execute(criteria: Criteria): List<Client> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "name" to "name",
                "lastname" to "lastname",
                "dni" to "dni",
                "birthday" to "birthday",
                "nationality" to "nationality",
                "cell_phone" to "cell_phone",
                "email" to "email"
            ),
            "personal_data",
            criteria
        )
        return jdbcTemplate.query(query) { rs: ResultSet, _: Int ->
            Client(
                rs.getString("name"),
                rs.getString("lastname"),
                rs.getString("dni"),
                rs.getString("birthday"),
                rs.getString("nationality"),
                rs.getString("cell_phone"),
                rs.getString("email")
            )
        }
    }

}