package com.brainycorp.tourism.packagee.adapter.out.jdbc

import com.brainycorp.tourism.shared.criteria.CriteriaToMySqlConverter
import com.brainycorp.tourism.packagee.adapter.out.jdbc.model.ServicePackageJdbcModel
import com.brainycorp.tourism.packagee.domain.Package
import com.brainycorp.tourism.packagee.application.port.out.SearchPackagesByCriteriaRepository
import com.brainycorp.tourism.shared.criteria.Criteria
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
                "tourist_services.cost" to "costS",
                "type_services.name" to "servicetypename",
                "tourist_package.pic" to "picP"
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
                rs.getString("servicetypename"),
                rs.getString("picP")?: ""
            )
        }

        return ServicePackageJdbcModel.fromDomain(packages)
    }
}