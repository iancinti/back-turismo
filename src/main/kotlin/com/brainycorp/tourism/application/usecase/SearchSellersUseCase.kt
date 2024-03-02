package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.SearchSellerQuery
import com.brainycorp.tourism.application.port.out.SearchSellerByCriterialRepository
import com.brainycorp.tourism.domain.*
import org.springframework.stereotype.Component

@Component
class SearchSellersUseCase(
    val searchSellerByCriterialRepository: SearchSellerByCriterialRepository
): SearchSellerQuery {
    override fun execute(searcher: String): List<Seller> {

        val criteria = Criteria.fromPrimitives(
            filtersOR = listOf(
                FiltersPrimitives("name", Operator.CONTAINS.name, searcher),
                FiltersPrimitives("lastname", Operator.CONTAINS.name, searcher),
                FiltersPrimitives("email", Operator.CONTAINS.name, searcher),
            ),
            filtersAND = listOf(),
            null,
            null
        )

        return searchSellerByCriterialRepository.execute(criteria)
    }

}