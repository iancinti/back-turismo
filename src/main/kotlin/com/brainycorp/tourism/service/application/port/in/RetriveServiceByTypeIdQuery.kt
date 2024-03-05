package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.service.domain.Service

interface RetriveServiceByTypeIdQuery {
    fun execute(typeId: Int): List<Service>
}