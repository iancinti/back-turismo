package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.domain.Service

interface CreateServiceCommand {
    fun execute(service: Service)
}