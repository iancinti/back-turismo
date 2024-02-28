package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Sale

interface SearchSalesByCriteriaRepository {
    fun execute(criteria: Criteria): List<Sale>

}