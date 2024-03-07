package com.brainycorp.tourism.client.adapter.out.jdbc

import com.brainycorp.tourism.client.application.port.out.DeleteClientRepository
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeleteClientMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteClientRepository {

    val DELETE_CLIENT: String = getSql("deleteClient")
    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_CLIENT, id)
    }

}