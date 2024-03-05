package com.brainycorp.tourism.packagee.application.usecase

import com.brainycorp.tourism.packagee.application.port.`in`.SearchPackagesQuery
import com.brainycorp.tourism.packagee.application.port.out.SearchPackagesByCriteriaRepository
import com.brainycorp.tourism.packagee.domain.Package
import com.brainycorp.tourism.shared.criteria.*
import org.springframework.stereotype.Component

@Component
class SearchPackageUseCase(
    val searchPackagesByCriteriaRepository: SearchPackagesByCriteriaRepository
) : SearchPackagesQuery {

    override fun execute(searchInput: String): List<Package> {
        val criteriaPackage: Criteria = Criteria.fromPrimitives(
            filtersOR =  listOf(
                FiltersPrimitives("tourist_package.name", Operator.CONTAINS.value, searchInput),
            ),
            filtersAND = listOf(),
            null,
            null,
            joins = listOf(
                Join("package_services", JoinType.JOIN, "tourist_package.code = package_services.code_package"),
                Join("tourist_services", JoinType.JOIN, "package_services.code_service = tourist_services.code"),
                Join("type_services", JoinType.JOIN, "type_services.id = tourist_services.type_id")
            )
        )

        return searchPackagesByCriteriaRepository.execute(criteriaPackage)
    }

}