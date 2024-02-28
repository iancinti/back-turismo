package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Service

interface RetriveServiceByIdQuery {
    fun execute (id: Int): Service
}