package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Client

interface UpdateClientCommand {
    fun execute(client: Client, clientId: String)
}