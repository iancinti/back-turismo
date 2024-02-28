package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Operator
import com.brainycorp.tourism.domain.Sale
import org.springframework.stereotype.Component


@Component
class SearchSalesUseCase(
    val searchSalesByCriteriaRepository: SearchSalesByCriteriaRepository
): SearchSalesQuery {

    override fun execute(searcher: String): List<Sale> {
        val criteria = Criteria.fromPrimitives(
            listOf(
                FiltersPrimitives("payment_method", Operator.CONTAINS.name, searcher)
            ),
            listOf(),
            null,
            null,
        )
        return searchSalesByCriteriaRepository.execute(criteria)

    }

}