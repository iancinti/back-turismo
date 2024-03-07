package com.brainycorp.tourism.packagee.adapter.out.jdbc

import com.brainycorp.tourism.packagee.application.port.out.UpdatePackageRepository
import com.brainycorp.tourism.packagee.domain.Package
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


@Component
class UpdatePackageMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdatePackageRepository {
    val UPDATE_PACKAGE: String = getSql("updatePackage")
    override fun execute(packag: Package, packageCode: String) {

        jdbcTemplate.update(
            UPDATE_PACKAGE,
            packag.name,
            packag.destination,
            packag.cost,
            packag.pic,
            packageCode
        )
    }
}