package com.brainycorp.tourism.print.application.port.`in`

import com.brainycorp.tourism.client.domain.Client

interface RetrivePdfClientsCommand {

    fun execute(): List<Client>
}
