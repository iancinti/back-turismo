package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.UpdateServiceCommand
import com.brainycorp.tourism.service.application.port.out.UpdateServiceRepository
import com.brainycorp.tourism.service.domain.Service
import org.springframework.stereotype.Component

@Component
class UpdateServiceUseCase(
    val updateServiceRepository: UpdateServiceRepository
): UpdateServiceCommand {


    override fun execute(service: Service, code: String) {
        updateServiceRepository.execute(service, code)
    }
}