package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.`in`.UpdateSaleCommand
import com.brainycorp.tourism.sales.application.port.out.UpdateSaleRepository
import org.springframework.stereotype.Component

@Component
class UpdateSaleUseCase(
    val updateSaleRepository: UpdateSaleRepository
): UpdateSaleCommand {

    override fun execute(sale: SaleRequest, saleId: String) {
        updateSaleRepository.execute(sale,saleId)
    }

}