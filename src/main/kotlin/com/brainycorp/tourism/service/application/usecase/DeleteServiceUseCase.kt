package com.brainycorp.tourism.service.application.usecase

import com.brainycorp.tourism.service.application.port.`in`.DeleteServiceCommand
import com.brainycorp.tourism.service.application.port.out.DeleteServiceRepository
import org.springframework.stereotype.Component

@Component
class DeleteServiceUseCase(
    val deleteServiceRepository: DeleteServiceRepository
): DeleteServiceCommand {


    override fun execute(code: String) {
        deleteServiceRepository.execute(code)
    }
}