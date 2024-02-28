package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Service

interface RetriveServiceByTypeIdQuery {
    fun execute(typeId: Int): List<Service>
}