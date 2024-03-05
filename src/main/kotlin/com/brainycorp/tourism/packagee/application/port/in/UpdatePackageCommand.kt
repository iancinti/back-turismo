package com.brainycorp.tourism.packagee.application.port.`in`

import com.brainycorp.tourism.packagee.domain.Package

interface UpdatePackageCommand {

    fun execute(packag: Package, packageCode: String)
}