package com.brainycorp.tourism.service.adapter.out.jdbc

import com.brainycorp.tourism.shared.criteria.CriteriaToMySqlConverter
import com.brainycorp.tourism.service.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.service.domain.Service
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
                "tourist_services.code" to "code",
                "tourist_services.description" to "description",
                "tourist_services.destination" to "destination",
                "tourist_services.date" to "date",
                "tourist_services.cost" to "cost",
                "type_services.name" to "typeName",
                "tourist_services.pic" to "picS"
            ),
            "tourist_services",
            criteria
        )

        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
            Service(
                rs.getInt("code"),
                rs.getString("typeName"),
                rs.getString("description"),
                rs.getString("destination"),
                rs.getString("date"),
                rs.getDouble("cost"),
                if(rs.getString("picS") != null ) rs.getString("picS") else "https://img.freepik.com/free-vector/front-view-sketch-fuck-you-symbol_23-2148667363.jpg"
            )
        }
    }
}