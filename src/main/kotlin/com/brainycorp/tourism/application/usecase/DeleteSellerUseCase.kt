package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.DeleteSellerCommand
import com.brainycorp.tourism.application.port.out.DeleteSellerRepository
import org.springframework.stereotype.Component

@Component
class DeleteSellerUseCase(
    val deleteSellerRepository: DeleteSellerRepository
): DeleteSellerCommand {

    override fun execute(id: String) {
        deleteSellerRepository.execute(id)
    }
}