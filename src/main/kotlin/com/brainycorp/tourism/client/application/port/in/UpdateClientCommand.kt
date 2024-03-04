package com.brainycorp.tourism.client.application.port.`in`

import com.brainycorp.tourism.domain.Client

interface UpdateClientCommand {
    fun execute(client: Client, clientId: String)
}