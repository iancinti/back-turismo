package com.brainycorp.tourism.client.adapter.out.jdbc

import com.brainycorp.tourism.client.application.port.out.UpdateClientRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UpdateClientMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdateClientRepository {

    val UPDATE_CLIENT: String = getSql("updateClient")
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