package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class RetriveServicesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): RetriveServicesByCriteriaRepository {
    override fun execute(criteria: Criteria): List<Service> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "code" to "code",
                "description" to "description",
                "destination" to "destination",
                "date" to "date",
                "cost" to "cost",
                "type_services.name" to "typeName"
            ),
            "tourist_services",
            criteria
        )

        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
            Service(
                rs.getString("code"),
                rs.getString("typeName"),
                rs.getString("description"),
                rs.getString("destination"),
                rs.getString("date"),
                rs.getDouble("cost")
            )
        }
    }
}