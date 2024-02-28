package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Package

interface CreatePackageCommand {

    fun execute(packag: Package)
}
