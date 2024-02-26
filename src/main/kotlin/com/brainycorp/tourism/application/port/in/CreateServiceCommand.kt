package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Service

interface CreateServiceCommand {
    fun execute(service: Service)
}