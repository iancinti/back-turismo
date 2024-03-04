package com.brainycorp.tourism.packagee.application.port.out

import com.brainycorp.tourism.domain.Package

interface CreatePackageRepository {

    fun execute(packag: Package)

}