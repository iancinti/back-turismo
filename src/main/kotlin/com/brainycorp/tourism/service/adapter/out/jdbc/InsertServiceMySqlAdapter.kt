package com.brainycorp.tourism.service.adapter.out.jdbc

import com.brainycorp.tourism.service.application.port.out.CreateServiceRepository
import com.brainycorp.tourism.service.domain.Service
import com.brainycorp.tourism.shared.FileReader.Companion.INSERT_SERVICE
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class InsertServiceMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateServiceRepository {

    override fun execute(service: Service){
        try {
            jdbcTemplate.update(
                INSERT_SERVICE,
                "1",
                service.description ,
                service.destination,
                service.cost
            )
        }catch (e: Throwable){
            println(e.message)

        }
    }

}