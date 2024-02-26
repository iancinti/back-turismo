package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Package

interface SearchPackagesQuery {

    fun execute(searchInput: String): List<Package>
}