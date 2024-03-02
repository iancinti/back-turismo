package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.RetriveClientByIdQuery
import com.brainycorp.tourism.application.port.out.RetriveClientsByCriteriaRepository
import com.brainycorp.tourism.domain.*
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
