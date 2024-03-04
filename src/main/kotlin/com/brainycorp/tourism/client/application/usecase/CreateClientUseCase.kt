package com.brainycorp.tourism.client.application.usecase

import com.brainycorp.tourism.client.application.port.`in`.CreateClientCommand
import com.brainycorp.tourism.client.application.port.out.CreateClientRepository
import com.brainycorp.tourism.domain.Client
import org.springframework.stereotype.Component

@Component
class CreateClientUseCase(
    val createClientRepository: CreateClientRepository
): CreateClientCommand {

    override fun execute(client: Client) {
        createClientRepository.execute(client)
    }
}