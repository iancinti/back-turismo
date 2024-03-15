package com.brainycorp.tourism.print.application.usecase

import com.brainycorp.tourism.client.application.port.out.SearchClientsByCriteriaRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.print.application.port.`in`.RetrivePdfClientsCommand
import com.brainycorp.tourism.shared.criteria.Criteria
import org.springframework.stereotype.Component

@Component
class RetrivePdfClientsUseCase(
     val searchClientsByCriteriaRepository: SearchClientsByCriteriaRepository
): RetrivePdfClientsCommand {
    override fun execute(): List<Client> {
        val criteria = Criteria.fromPrimitives(emptyList(), emptyList(), null, null)
        return searchClientsByCriteriaRepository.execute(criteria)
    }

}
