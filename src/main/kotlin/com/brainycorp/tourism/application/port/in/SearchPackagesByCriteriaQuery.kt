package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Package

interface SearchPackagesByCriteriaQuery {

    fun execute(): List<Package>
}