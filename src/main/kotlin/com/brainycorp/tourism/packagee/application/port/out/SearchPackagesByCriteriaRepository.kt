package com.brainycorp.tourism.packagee.application.port.out

import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.packagee.domain.Package

interface SearchPackagesByCriteriaRepository {

    fun execute(criteria: Criteria): List<Package>
}