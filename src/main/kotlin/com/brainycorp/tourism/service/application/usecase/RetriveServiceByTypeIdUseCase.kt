package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.RetriveServiceByTypeIdQuery
import com.brainycorp.tourism.service.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.shared.criteria.Criteria
import com.brainycorp.tourism.shared.criteria.FiltersPrimitives
import com.brainycorp.tourism.shared.criteria.Operator
import com.brainycorp.tourism.service.domain.Service
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