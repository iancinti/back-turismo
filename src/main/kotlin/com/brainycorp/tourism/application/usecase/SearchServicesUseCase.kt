package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.SearchServicesQuery
import com.brainycorp.tourism.application.port.out.SearchServicesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Service
import org.springframework.stereotype.Component

@Component
class SearchServicesUseCase(
    private val searchServicesByCriteriaRepository: SearchServicesByCriteriaRepository
): SearchServicesQuery {


    override fun execute(searchInput: String): List<Service> {
        val criteria: Criteria = Criteria.fromPrimitives(
            filtersOR =  listOf(
                FiltersPrimitives("description", "CONTAINS", searchInput),
                FiltersPrimitives("destination", "CONTAINS", searchInput)
            ),
            filtersAND = listOf(),
            "cost",
            "ASC"
        )
        return searchServicesByCriteriaRepository.execute(criteria)
    }
}