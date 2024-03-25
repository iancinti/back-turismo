package com.brainycorp.tourism.client.adapter.out.jdbc

import com.brainycorp.tourism.shared.criteria.CriteriaToMySqlConverter
import com.brainycorp.tourism.client.application.port.out.RetriveClientsByCriteriaRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.criteria.Criteria
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class RetriveClientsByCriteriaMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): RetriveClientsByCriteriaRepository {

    val log = LoggerFactory.getLogger("RetriveClientsByCriteriaMySqlAdapter")

    override fun execute(criteria: Criteria): List<Client> {
        val query = CriteriaToMySqlConverter.convert(
            mapOf(
                "id" to "id",
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
        log.info("Se ejecuta query de filtrado de cliente: $query")
        return jdbcTemplate.query(query) { rs: ResultSet, _: Int ->
            Client(
                rs.getInt("id").toString(),
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