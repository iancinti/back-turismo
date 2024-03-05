package com.brainycorp.tourism.seller.application.port.out

import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.seller.domain.Seller

interface SearchSellerByCriterialRepository {

    fun execute(criteria: Criteria): List<Seller>

}