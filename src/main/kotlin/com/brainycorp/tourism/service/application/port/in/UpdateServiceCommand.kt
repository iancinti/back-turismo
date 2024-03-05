package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.service.domain.Service

interface UpdateServiceCommand {

    fun execute(service: Service, code: String)
}