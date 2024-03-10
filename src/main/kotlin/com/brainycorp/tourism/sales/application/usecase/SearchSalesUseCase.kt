package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse
import com.brainycorp.tourism.sales.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.sales.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.shared.criteria.*
import org.springframework.stereotype.Component


@Component
class SearchSalesUseCase(
    val searchSalesByCriteriaRepository: SearchSalesByCriteriaRepository
): SearchSalesQuery {

    override fun execute(searcher: String): List<SaleResponse> {
        val criteria = Criteria.fromPrimitives(
            listOf(
                FiltersPrimitives("payment_method", Operator.CONTAINS.name, searcher)
            ),
            listOf(
                FiltersPrimitives("sales.delete_at", Operator.EQUAL.name, "")
            ),
            null,
            null,
            joins = listOf(
                Join("clients", JoinType.JOIN, "sales.client_id = clients.id"),
                Join("personal_data", JoinType.JOIN, "clients.personal_data_id = personal_data.personal_data_id"),
            )
        )
        return searchSalesByCriteriaRepository.execute(criteria)

    }

}