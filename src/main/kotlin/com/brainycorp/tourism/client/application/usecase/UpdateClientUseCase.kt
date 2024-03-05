package com.brainycorp.tourism.client.application.usecase

import com.brainycorp.tourism.client.application.port.`in`.UpdateClientCommand
import com.brainycorp.tourism.client.application.port.out.UpdateClientRepository
import com.brainycorp.tourism.client.domain.Client
import org.springframework.stereotype.Component

@Component
class UpdateClientUseCase(
    val updateClientRepository: UpdateClientRepository
): UpdateClientCommand {

    override fun execute(client: Client, clientId: String) {
        updateClientRepository.execute(client, clientId)
    }
}