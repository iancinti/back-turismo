package com.brainycorp.tourism.sales.application.port.out

import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.sales.domain.Sale

interface SearchSalesByCriteriaRepository {
    fun execute(criteria: Criteria): List<Sale>

}