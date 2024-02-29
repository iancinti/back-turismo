package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Client

interface CreateClientRepository {

    fun execute(client: Client)

}