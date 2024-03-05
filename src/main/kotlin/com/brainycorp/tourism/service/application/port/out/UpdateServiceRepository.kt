package com.brainycorp.tourism.service.application.port.out

import com.brainycorp.tourism.service.domain.Service

interface UpdateServiceRepository {

    fun execute(service: Service, code: String)
}