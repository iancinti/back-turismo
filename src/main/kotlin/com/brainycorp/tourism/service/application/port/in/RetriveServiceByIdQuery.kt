package com.brainycorp.tourism.service.application.port.`in`

import com.brainycorp.tourism.service.domain.Service

interface RetriveServiceByIdQuery {
    fun execute (code: Int): Service
}