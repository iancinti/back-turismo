package com.brainycorp.tourism.packagee.application.port.`in`

import com.brainycorp.tourism.packagee.domain.Package

interface CreatePackageCommand {

    fun execute(packag: Package): Int
}
