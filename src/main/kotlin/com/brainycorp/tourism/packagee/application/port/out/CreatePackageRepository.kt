package com.brainycorp.tourism.packagee.application.port.out

import com.brainycorp.tourism.packagee.domain.Package

interface CreatePackageRepository {

    fun execute(packag: Package)

}