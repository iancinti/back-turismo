package com.brainycorp.tourism.adapter.out.jdbc

import com.brainycorp.tourism.application.port.out.CreateServiceRepository
import com.brainycorp.tourism.domain.Service
import com.brainycorp.tourism.util.FileReader.Companion.INSERT_SERVICE
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