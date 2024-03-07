package com.brainycorp.tourism.client.adapter.out.jdbc

import com.brainycorp.tourism.client.application.port.out.CreateClientRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.DateParser
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Component
class InsertClientMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateClientRepository {
    val INSERT_PERSONAL_DATA: String = getSql("insertPersonalData")
    @Transactional
    override fun execute(client: Client){
        try {
            val keyHolder = GeneratedKeyHolder()
            jdbcTemplate.update({ con ->
                    val ps = con.prepareStatement(INSERT_PERSONAL_DATA, arrayOf("personal_data_id"))
                    ps.setString(1, client.name)
                    ps.setString(2, client.lastname)
                    ps.setString(3, client.dni)
                    val birthdayUtilDate = DateParser.convertStringToDate(client.birthday!!)
                    val birthdaySqlDate = Date(birthdayUtilDate.time)
                    ps.setDate(4, birthdaySqlDate)
                    ps.setString(5, client.nationality)
                    ps.setString(6, client.cellPhone)
                    ps.setString(7, client.email)
                    ps
                }, keyHolder
            )

            val pk = keyHolder.key?.toInt()

            jdbcTemplate.update("INSERT INTO clients (personal_data_id) VALUES (?)", pk)
        }catch (e: Throwable){
            println(e.message)

        }
    }

}