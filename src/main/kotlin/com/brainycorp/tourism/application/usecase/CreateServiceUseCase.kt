package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.CreateServiceCommand
import com.brainycorp.tourism.application.port.out.CreateServiceRepository
import com.brainycorp.tourism.domain.Service
import org.springframework.stereotype.Component

@Component
class CreateServiceUseCase(
    val createServiceRepository: CreateServiceRepository
): CreateServiceCommand {

    override fun execute(service: Service) {
        createServiceRepository.execute(service)
    }
}