package com.brainycorp.tourism.service.adapter.out.jdbc

import com.brainycorp.tourism.service.application.port.out.DeleteServiceRepository
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeleteServiceMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeleteServiceRepository {
    val DELETE_SERVICE: String = getSql("deleteService")
    override fun execute(code: String) {
        jdbcTemplate.update(DELETE_SERVICE, code)
    }
}