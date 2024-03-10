package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse
import com.brainycorp.tourism.sales.application.port.`in`.RetriveSaleByIdQuery
import com.brainycorp.tourism.sales.application.port.out.SearchSalesByCriteriaRepository
import com.brainycorp.tourism.sales.domain.Sale
import com.brainycorp.tourism.shared.criteria.*
import org.springframework.stereotype.Component

@Component
class RetriveSaleByIdUseCase(
    val retriveSalesByCriteriaRepository: SearchSalesByCriteriaRepository
): RetriveSaleByIdQuery {

    override fun execute(id: Int): SaleResponse {
        val criteria = Criteria.fromPrimitives(
            listOf(),
            listOf(
                FiltersPrimitives("sales.id", Operator.EQUAL.name, id.toString())
            ),
            null,
            null,
            listOf(
                Join("tourist_package", JoinType.JOIN, "sales.package_id = tourist_package.code"),
                Join("tourist_services", JoinType.JOIN, "sales.service_id = tourist_services.code"),
                Join("clients", JoinType.JOIN, "sales.client_id = clients.id"),
                Join("personal_data", JoinType.JOIN,"clients.personal_data_id = personal_data.personal_data_id"),
            )
        )
        return retriveSalesByCriteriaRepository.execute(criteria).get(0)
    }
}