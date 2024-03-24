package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.application.port.`in`.DeleteSaleCommand
import com.brainycorp.tourism.sales.application.port.out.DeleteSaleRepository
import org.springframework.stereotype.Component

@Component
class DeleteSaleUseCase(
    val deleteSaleRepository: DeleteSaleRepository
): DeleteSaleCommand {
    override fun execute(id: String) {
        deleteSaleRepository.execute(id)
    }
}