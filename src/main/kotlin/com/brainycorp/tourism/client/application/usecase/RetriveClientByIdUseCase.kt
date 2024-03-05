package com.brainycorp.tourism.client.application.usecase

import com.brainycorp.tourism.client.application.port.`in`.RetriveClientByIdQuery
import com.brainycorp.tourism.client.application.port.out.RetriveClientsByCriteriaRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.shared.criteria.FiltersPrimitives
import com.brainycorp.tourism.shared.criteria.Operator
import org.springframework.stereotype.Component

@Component
class RetriveClientByIdUseCase(
    val retriveClientsByCriteriaRepository: RetriveClientsByCriteriaRepository
): RetriveClientByIdQuery {
    override fun execute(id: Int): Client {
        val criteria = Criteria.fromPrimitives(
            listOf(),
            listOf(
                FiltersPrimitives("dni", Operator.EQUAL.name, id.toString())
            ),
            null,
            null
        )
        return retriveClientsByCriteriaRepository.execute(criteria).get(0)
    }
}
