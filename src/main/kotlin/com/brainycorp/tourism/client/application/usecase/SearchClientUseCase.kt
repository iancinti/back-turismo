package com.brainycorp.tourism.client.application.usecase

import com.brainycorp.tourism.client.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.client.application.port.out.SearchClientsByCriteriaRepository
import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.shared.criteria.*
import org.slf4j.LoggerFactory
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
            filtersAND = listOf(
                FiltersPrimitives("clients.delete_at", Operator.EQUAL.name, ""),
            ),
            null,
            null,
            joins = listOf(
                Join("clients", JoinType.JOIN, "personal_data.personal_data_id = clients.personal_data_id")
            )
        )

        return searchClientsByCriteriaRepository.execute(criteria)
    }

}