package com.brainycorp.tourism.packagee.application.port.out

import com.brainycorp.tourism.packagee.domain.Package

interface UpdatePackageRepository {

    fun execute(packag: Package, packageCode: String)
}