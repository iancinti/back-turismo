package com.brainycorp.tourism.packagee.adapter.out.jdbc

import com.brainycorp.tourism.packagee.application.port.out.CreatePackageRepository
import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.util.FileReader.Companion.INSERT_PACKAGE
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class InsertPackageMySqlAdapter(val jdbcTemplate: JdbcTemplate): CreatePackageRepository {

    override fun execute(packag: Package){
        try {
            jdbcTemplate.update(
                INSERT_PACKAGE,
                packag.name,
                packag.destination,
                packag.cost
            )
        }catch (e: Throwable){
            println(e.message)

        }
    }

}