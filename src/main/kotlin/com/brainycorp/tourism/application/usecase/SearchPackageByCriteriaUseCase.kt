package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.`in`.SearchPackagesByCriteriaQuery
import com.brainycorp.tourism.application.port.out.SearchPackagesByCriteriaRepository
import org.springframework.stereotype.Component

@Component
class SearchPackageByCriteriaUseCase(
    val searchPackagesByCriteriaRepository: SearchPackagesByCriteriaRepository
) : SearchPackagesByCriteriaQuery{

    override fun execute(): List<Package> {
        return searchPackagesByCriteriaRepository.execute()
    }

}