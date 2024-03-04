package com.brainycorp.tourism.service.application.port.out

import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Service

interface RetriveServicesByCriteriaRepository {

    fun execute(criteria: Criteria): List<Service>
}