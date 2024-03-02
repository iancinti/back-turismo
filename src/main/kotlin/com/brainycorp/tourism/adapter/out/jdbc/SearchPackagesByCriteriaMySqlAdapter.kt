package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToMySqlConverter
import com.brainycorp.tourism.adapter.out.jdbc.model.ServicePackageJdbcModel
import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.out.SearchPackagesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchPackagesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): SearchPackagesByCriteriaRepository {

    override fun execute(criteria: Criteria): List<Package> {

        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "tourist_package.code" to "codeP",
                "tourist_package.name" to "nameP",
                "tourist_package.destination" to "destinationP",
                "tourist_package.cost" to "costP",
                "tourist_services.code" to "codeS",
                "tourist_services.description" to "descriptionS",
                "tourist_services.date" to "dateS",
                "tourist_services.cost" to "costS"
            ),
            "tourist_package",
            criteria
        )

        val packages = jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
            ServicePackageJdbcModel(
                rs.getString("codeP"),
                rs.getString("nameP"),
                rs.getString("destinationP"),
                rs.getString("costP"),
                rs.getString("codeS"),
                rs.getString("descriptionS"),
                rs.getString("dateS"),
                rs.getString("costS"),
            )
        }

        return ServicePackageJdbcModel.fromDomain(packages)
    }
}