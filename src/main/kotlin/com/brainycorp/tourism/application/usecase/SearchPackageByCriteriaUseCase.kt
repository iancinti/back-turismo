package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.`in`.SearchPackagesByCriteriaQuery
import com.brainycorp.tourism.application.port.out.SearchPackagesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import org.springframework.stereotype.Component

@Component
class SearchPackageByCriteriaUseCase(
    val searchPackagesByCriteriaRepository: SearchPackagesByCriteriaRepository
) : SearchPackagesByCriteriaQuery{

    override fun execute(searchInput: String): List<Package> {
        val criteria: Criteria = Criteria.fromPrimitives(
            listOf(
                FiltersPrimitives("name", "LIKE", searchInput),
                FiltersPrimitives("destination", "LIKE", searchInput)
            ),
            "cost",
            "ASC"
        )

        return searchPackagesByCriteriaRepository.execute(criteria)
    }

}