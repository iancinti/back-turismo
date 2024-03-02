package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Client
import com.brainycorp.tourism.domain.Criteria

interface RetriveClientsByCriteriaRepository {

    fun execute(criteria: Criteria): List<Client>

}