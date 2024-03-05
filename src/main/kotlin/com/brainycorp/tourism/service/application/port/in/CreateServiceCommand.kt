package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.service.domain.Service

interface CreateServiceCommand {
    fun execute(service: Service)
}