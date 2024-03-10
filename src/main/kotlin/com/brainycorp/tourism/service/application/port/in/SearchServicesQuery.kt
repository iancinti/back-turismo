package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.service.domain.Service

interface SearchServicesQuery {

    fun execute(searchInput: String, typeId: String?): List<Service>
}