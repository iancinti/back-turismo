package com.brainycorp.tourism.service.adapter.out.jdbc

import com.brainycorp.tourism.service.application.port.out.CreateServiceRepository
import com.brainycorp.tourism.service.domain.Service
import com.brainycorp.tourism.shared.DateParser.Companion.convertFromIsoString
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.Timestamp

@Component
class InsertServiceMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateServiceRepository {
    val INSERT_SERVICE: String = getSql("insertService")
    override fun execute(service: Service){
        try {
            val localDateTime = convertFromIsoString(service.date ?: throw IllegalArgumentException("Date is required"))

            jdbcTemplate.update(
                INSERT_SERVICE,
                service.type,
                service.description ,
                service.destination,
                service.cost,
                service.pic,
                Timestamp.valueOf(localDateTime)
            )
        }catch (e: Throwable){
            println(e.message)

        }
    }

}