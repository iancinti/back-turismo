package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.UpdateClientCommand
import com.brainycorp.tourism.application.port.out.UpdateClientRepository
import com.brainycorp.tourism.domain.Client
import org.springframework.stereotype.Component

@Component
class UpdateClientUseCase(
    val updateClientRepository: UpdateClientRepository
): UpdateClientCommand {

    override fun execute(client: Client, clientId: String) {
        updateClientRepository.execute(client, clientId)
    }
}