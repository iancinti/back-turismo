package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.application.port.out.SearchClientsByCriteriaRepository
import com.brainycorp.tourism.domain.Client
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Operator
import org.springframework.stereotype.Component

@Component
class SearchClientUseCase(
    val searchClientsByCriteriaRepository: SearchClientsByCriteriaRepository
): SearchClientQuery {
    override fun execute(searcher: String): List<Client> {

        val criteria = Criteria.fromPrimitives(
            filtersOR = listOf(
                FiltersPrimitives("name", Operator.CONTAINS.name, searcher),
                FiltersPrimitives("lastname", Operator.CONTAINS.name, searcher),
                FiltersPrimitives("email", Operator.CONTAINS.name, searcher),
            ),
            filtersAND = listOf(),
            null,
            null
        )

        return searchClientsByCriteriaRepository.execute(criteria)
    }

}