package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.domain.Service

interface SearchServicesQuery {

    fun execute(searchInput: String): List<Service>
}