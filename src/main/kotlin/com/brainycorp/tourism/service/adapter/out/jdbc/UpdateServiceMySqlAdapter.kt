package com.brainycorp.tourism.service.adapter.out.jdbc

import com.brainycorp.tourism.service.application.port.out.UpdateServiceRepository
import com.brainycorp.tourism.service.domain.Service
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UpdateServiceMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdateServiceRepository {

    val UPDATE_SERVICE: String = getSql("updateService")

    override fun execute(service: Service, code: String) {

        jdbcTemplate.update(
            UPDATE_SERVICE,
            service.description,
            service.destination,
            service.date,
            service.cost,
            service.pic,
            code
        )
    }
}