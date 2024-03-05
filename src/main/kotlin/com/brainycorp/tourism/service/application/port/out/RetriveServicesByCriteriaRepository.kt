package com.brainycorp.tourism.service.application.port.out

import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.service.domain.Service

interface RetriveServicesByCriteriaRepository {

    fun execute(criteria: Criteria): List<Service>
}