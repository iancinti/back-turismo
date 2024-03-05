package com.brainycorp.tourism.client.application.port.out

import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.criteria.Criteria

interface SearchClientsByCriteriaRepository {
    fun execute(criteria: Criteria): List<Client>
}