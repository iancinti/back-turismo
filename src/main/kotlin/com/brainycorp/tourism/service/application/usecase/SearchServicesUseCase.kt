package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.SearchServicesQuery
import com.brainycorp.tourism.service.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.domain.*
import org.springframework.stereotype.Component

@Component
class SearchServicesUseCase(
    private val retriveServicesByCriteriaRepository: RetriveServicesByCriteriaRepository
): SearchServicesQuery {


    override fun execute(searchInput: String): List<Service> {
        val criteria: Criteria = Criteria.fromPrimitives(
            filtersOR =  listOf(
                FiltersPrimitives("description", "CONTAINS", searchInput),
                FiltersPrimitives("destination", "CONTAINS", searchInput)
            ),
            filtersAND = listOf(),
            "cost",
            "ASC",
            listOf(
                Join("type_services", JoinType.JOIN, "type_services.id = tourist_services.type_id")
            )
        )
        return retriveServicesByCriteriaRepository.execute(criteria)
    }
}