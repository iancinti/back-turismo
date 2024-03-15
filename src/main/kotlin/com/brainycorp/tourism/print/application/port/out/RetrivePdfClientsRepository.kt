package com.brainycorp.tourism.print.application.port.out

import com.brainycorp.tourism.client.domain.Client

interface RetrivePdfClientsRepository {

    fun execute(): List<Client>
}