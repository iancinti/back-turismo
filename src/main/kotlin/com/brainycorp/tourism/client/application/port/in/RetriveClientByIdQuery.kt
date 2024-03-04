package com.brainycorp.tourism.client.application.port.`in`

import com.brainycorp.tourism.domain.Client

interface RetriveClientByIdQuery {

    fun execute(id: Int): Client

}