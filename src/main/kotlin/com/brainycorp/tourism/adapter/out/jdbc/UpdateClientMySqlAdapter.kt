package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.application.port.out.UpdateClientRepository
import com.brainycorp.tourism.domain.Client
import com.brainycorp.tourism.util.FileReader.Companion.UPDATE_CLIENT
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UpdateClientMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdateClientRepository {

    override fun execute(client: Client, clientId: String) {

        jdbcTemplate.update(
            UPDATE_CLIENT,
            client.name,
            client.lastname,
            client.dni,
            client.birthday,
            client.nationality,
            client.cellPhone,
            client.email,
            clientId
        )
    }
}