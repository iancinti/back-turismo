package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Client

interface UpdateClientRepository {
    fun execute(client: Client, clientId: String)
}