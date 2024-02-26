package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Service

interface CreateServiceRepository {
    fun execute(service: Service)
}