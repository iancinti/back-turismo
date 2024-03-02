package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Client

interface RetriveClientByIdQuery {

    fun execute(id: Int): Client

}