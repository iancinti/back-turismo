package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.RetriveServiceByIdQuery
import com.brainycorp.tourism.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Operator
import com.brainycorp.tourism.domain.Service
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