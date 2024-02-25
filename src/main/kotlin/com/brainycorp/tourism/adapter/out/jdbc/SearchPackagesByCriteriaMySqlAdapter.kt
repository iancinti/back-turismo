package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.adapter.out.jdbc.converter.CriteriaToSqlConverter
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

        val query = CriteriaToSqlConverter.convert(
            listOf("code", "name", "destination", "cost"),
            "tourist_package",
            criteria
        )

        return jdbcTemplate.query(query) {
                rs: ResultSet, _: Int ->
            Package(
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("destination"),
                rs.getDouble("cost")
            )
        }
    }
}