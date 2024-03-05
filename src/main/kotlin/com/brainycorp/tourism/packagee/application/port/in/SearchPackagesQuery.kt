package com.brainycorp.tourism.packagee.application.port.`in`

import com.brainycorp.tourism.packagee.domain.Package

interface SearchPackagesQuery {

    fun execute(searchInput: String): List<Package>
}