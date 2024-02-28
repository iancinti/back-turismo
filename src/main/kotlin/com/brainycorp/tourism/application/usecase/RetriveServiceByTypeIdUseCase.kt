package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.RetriveServiceByTypeIdQuery
import com.brainycorp.tourism.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Operator
import com.brainycorp.tourism.domain.Service
import org.springframework.stereotype.Component

@Component
class RetriveServiceByTypeIdUseCase(
    val retriveServicesByCriteriaRepository: RetriveServicesByCriteriaRepository
): RetriveServiceByTypeIdQuery {
    override fun execute(typeId: Int): List<Service> {
        val criteria = Criteria.fromPrimitives(
            listOf(),
            listOf(
                FiltersPrimitives("type_id", Operator.EQUAL.name, typeId.toString())
            ),
            null,
            null
        )
        return retriveServicesByCriteriaRepository.execute(criteria)
    }
}