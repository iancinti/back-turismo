package com.brainycorp.tourism.service.application.port.out

import com.brainycorp.tourism.service.domain.Service

interface CreateServiceRepository {
    fun execute(service: Service)
}