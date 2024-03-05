package com.brainycorp.tourism.client.application.port.out

import com.brainycorp.tourism.client.domain.Client

interface UpdateClientRepository {
    fun execute(client: Client, clientId: String)
}