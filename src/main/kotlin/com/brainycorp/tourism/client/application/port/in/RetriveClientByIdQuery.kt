package com.brainycorp.tourism.client.application.port.`in`

import com.brainycorp.tourism.client.domain.Client

interface RetriveClientByIdQuery {

    fun execute(id: Int): Client

}