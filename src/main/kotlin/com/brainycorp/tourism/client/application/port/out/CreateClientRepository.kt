package com.brainycorp.tourism.client.application.port.out

import com.brainycorp.tourism.domain.Client

interface CreateClientRepository {

    fun execute(client: Client)

}