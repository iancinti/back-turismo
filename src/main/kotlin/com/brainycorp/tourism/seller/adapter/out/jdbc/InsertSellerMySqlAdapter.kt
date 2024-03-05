package com.brainycorp.tourism.seller.adapter.out.jdbc

import com.brainycorp.tourism.seller.application.port.out.CreateSellerRepository
import com.brainycorp.tourism.seller.domain.Seller
import com.brainycorp.tourism.util.DateParser
import com.brainycorp.tourism.util.FileReader
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Component
class InsertSellerMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreateSellerRepository {

    @Transactional
    override fun execute(seller: Seller){
        try {
            val keyHolder = GeneratedKeyHolder()
            jdbcTemplate.update({ con ->
                val ps = con.prepareStatement(FileReader.INSERT_PERSONAL_DATA, arrayOf("personal_data_id"))
                ps.setString(1, seller.name)
                ps.setString(2, seller.lastname)
                ps.setString(3, seller.dni)
                val birthdayUtilDate = DateParser.convertStringToDate(seller.birthday!!)
                val birthdaySqlDate = Date(birthdayUtilDate.time)
                ps.setDate(4, birthdaySqlDate)
                ps.setString(5, seller.nationality)
                ps.setString(6, seller.cellPhone)
                ps.setString(7, seller.email)
                ps
            }, keyHolder
            )

            val pk = keyHolder.key?.toInt()

            jdbcTemplate.update("INSERT INTO employees (personal_data_id) VALUES (?)", pk)
        }catch (e: Throwable){
            println(e.message)

        }
    }

}