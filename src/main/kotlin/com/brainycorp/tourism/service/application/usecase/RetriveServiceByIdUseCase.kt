package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.RetriveServiceByIdQuery
import com.brainycorp.tourism.service.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.shared.criteria.FiltersPrimitives
import com.brainycorp.tourism.shared.criteria.Operator
import com.brainycorp.tourism.service.domain.Service
import org.springframework.stereotype.Component

@Component
class RetriveServiceByIdUseCase(
    val retriveServicesByCriteriaRepository: RetriveServicesByCriteriaRepository
): RetriveServiceByIdQuery {
    override fun execute(id: Int): Service {
        val criteria = Criteria.fromPrimitives(
            listOf(),
            listOf(
                FiltersPrimitives("code", Operator.EQUAL.name, id.toString())
            ),
            null,
            null
        )
        return retriveServicesByCriteriaRepository.execute(criteria).get(0)
    }
}