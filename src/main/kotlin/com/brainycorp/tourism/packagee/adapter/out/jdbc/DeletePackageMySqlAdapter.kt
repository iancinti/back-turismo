package com.brainycorp.tourism.packagee.adapter.out.jdbc

import com.brainycorp.tourism.packagee.application.port.out.DeletePackageRepository
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class DeletePackageMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): DeletePackageRepository {
    val DELETE_PACKAGE: String = getSql("deletePackage")
    override fun execute(code: String) {
        jdbcTemplate.update(DELETE_PACKAGE, code)
    }
}