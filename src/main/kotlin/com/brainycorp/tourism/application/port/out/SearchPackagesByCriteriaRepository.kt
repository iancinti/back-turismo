package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Package

interface SearchPackagesByCriteriaRepository {

    fun execute(): List<Package>
}