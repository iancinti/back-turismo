package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.application.port.out.DeleteClientRepository
import com.brainycorp.tourism.util.FileReader.Companion.DELETE_CLIENT
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeleteClientMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteClientRepository {


    override fun execute(id: String) {
        jdbcTemplate.update(DELETE_CLIENT, id)
    }

}