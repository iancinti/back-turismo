package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.`in`.SearchPackagesQuery
import com.brainycorp.tourism.application.port.out.SearchPackagesByCriteriaRepository
import com.brainycorp.tourism.domain.Criteria
import com.brainycorp.tourism.domain.FiltersPrimitives
import com.brainycorp.tourism.domain.Operator
import org.springframework.stereotype.Component

@Component
class SearchPackageUseCase(
    val searchPackagesByCriteriaRepository: SearchPackagesByCriteriaRepository
) : SearchPackagesQuery{

    override fun execute(searchInput: String): List<Package> {
        val criteria: Criteria = Criteria.fromPrimitives(
            filtersOR =  listOf(
                FiltersPrimitives("name", Operator.CONTAINS.value, searchInput),
                FiltersPrimitives("destination", Operator.CONTAINS.value, searchInput)
            ),
            filtersAND = listOf(),
            "cost",
            "ASC"
        )

        return searchPackagesByCriteriaRepository.execute(criteria)
    }

}