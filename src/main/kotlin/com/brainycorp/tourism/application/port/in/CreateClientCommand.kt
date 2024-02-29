package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Client

interface CreateClientCommand {

    fun execute(client: Client)

}