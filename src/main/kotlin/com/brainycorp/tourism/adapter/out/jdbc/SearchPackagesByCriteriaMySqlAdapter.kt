package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.out.SearchPackagesByCriteriaRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class SearchPackagesByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): SearchPackagesByCriteriaRepository {


    override fun execute(): List<Package> {
        return jdbcTemplate.query("SELECT code, name, destination, cost FROM tourist_package") {
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