package com.brainycorp.tourism.packagee.adapter.out.jdbc

import com.brainycorp.tourism.packagee.application.port.out.CreatePackageRepository
import com.brainycorp.tourism.packagee.domain.Package
import com.brainycorp.tourism.shared.FileReader.Companion.INSERT_PACKAGE
import com.brainycorp.tourism.shared.FileReader.Companion.INSERT_SERVICE_PACKAGE
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Component

@Component
class InsertPackageMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreatePackageRepository {

    override fun execute(packag: Package){
        try {
            val keyHolder :KeyHolder = GeneratedKeyHolder()
            jdbcTemplate.update({
                val ps = it.prepareStatement(INSERT_PACKAGE, arrayOf("code"))
                ps.setString(1, packag.name)
                ps.setString(2, packag.destination)
                ps.setDouble(3, packag.cost?:0.0)
                ps
            }, keyHolder)
            val generatedCode = keyHolder.key?.toInt()
            packag.services?.forEach {
                jdbcTemplate.update(INSERT_SERVICE_PACKAGE, generatedCode, it.code)
            }
        }catch (e: DataAccessException){
            println(e.message)

        }
    }

}