package com.brainycorp.tourism.packagee.application.port.`in`

import com.brainycorp.tourism.packagee.domain.Package

interface RetrivePackageByCodeQuery {

    fun execute(code: String): Package
}