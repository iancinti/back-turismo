package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.SearchServicesQuery
import com.brainycorp.tourism.service.application.port.out.RetriveServicesByCriteriaRepository
import com.brainycorp.tourism.service.domain.Service
import com.brainycorp.tourism.shared.criteria.*
import org.springframework.stereotype.Component

@Component
class SearchServicesUseCase(
    private val retriveServicesByCriteriaRepository: RetriveServicesByCriteriaRepository
): SearchServicesQuery {


    override fun execute(searchInput: String, typeId: String?): List<Service> {

        val filtersOr: MutableList<FiltersPrimitives> = mutableListOf()
        filtersOr += FiltersPrimitives("description", "CONTAINS", searchInput)
        filtersOr += FiltersPrimitives("destination", "CONTAINS", searchInput)


        val filtersAnd: MutableList<FiltersPrimitives> = mutableListOf()
        filtersAnd += FiltersPrimitives("tourist_services.delete_at", Operator.EQUAL.name, "")
        if (typeId != null) {
            if (typeId.isNotEmpty()){
                filtersAnd += FiltersPrimitives("type_services.name", Operator.EQUAL.name, typeId.toString())
            }
        }

        val criteria: Criteria = Criteria.fromPrimitives(
            filtersOR =  filtersOr,
            filtersAND = filtersAnd,
            "cost",
            "ASC",
            listOf(
                Join("type_services", JoinType.JOIN, "type_services.id = tourist_services.type_id")
            )
        )
        return retriveServicesByCriteriaRepository.execute(criteria)
    }
}