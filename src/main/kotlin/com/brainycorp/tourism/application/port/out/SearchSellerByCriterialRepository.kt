package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.Seller

interface SearchSellerByCriterialRepository {

    fun execute(criteria: Criteria): List<Seller>

}