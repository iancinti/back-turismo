package com.brainycorp.tourism.client.application.port.`in`

import com.brainycorp.tourism.client.domain.Client

interface CreateClientCommand {

    fun execute(client: Client)

}