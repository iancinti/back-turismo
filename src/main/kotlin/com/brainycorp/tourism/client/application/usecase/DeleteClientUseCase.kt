package com.brainycorp.tourism.client.application.usecase

import com.brainycorp.tourism.client.application.port.`in`.DeleteClientCommand
import com.brainycorp.tourism.client.application.port.out.DeleteClientRepository
import org.springframework.stereotype.Component

@Component
class DeleteClientUseCase(
    val deleteClientRepository: DeleteClientRepository
): DeleteClientCommand {

    override fun execute(id: String) {
        deleteClientRepository.execute(id)
    }
}