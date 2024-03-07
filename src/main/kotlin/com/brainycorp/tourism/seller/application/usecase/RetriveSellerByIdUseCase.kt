package com.brainycorp.tourism.seller.application.usecase

import com.brainycorp.tourism.seller.application.port.`in`.RetriveSellerByIdQuery
import com.brainycorp.tourism.seller.application.port.out.SearchSellerByCriterialRepository
import com.brainycorp.tourism.seller.domain.Seller
import com.brainycorp.tourism.shared.criteria.*
import org.springframework.stereotype.Component

@Component
class RetriveSellerByIdUseCase(
    val retriveSellerByCriterialRepository: SearchSellerByCriterialRepository
): RetriveSellerByIdQuery {

    override fun execute(id: Int): Seller {
        val criteria = Criteria.fromPrimitives(
            listOf(),
            listOf(
                FiltersPrimitives("personal_data.personal_data_id", Operator.EQUAL.name, id.toString())
            ),
            null,
            null,
            joins = listOf(
                Join("employees", JoinType.JOIN, "employees.personal_data_id = personal_data.personal_data_id")
            )
        )
        return retriveSellerByCriterialRepository.execute(criteria).get(0)
    }
}