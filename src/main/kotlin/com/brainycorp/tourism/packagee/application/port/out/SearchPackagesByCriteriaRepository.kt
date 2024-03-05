package com.brainycorp.tourism.packagee.application.port.out

import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.packagee.domain.Package

interface SearchPackagesByCriteriaRepository {

    fun execute(criteria: Criteria): List<Package>
}