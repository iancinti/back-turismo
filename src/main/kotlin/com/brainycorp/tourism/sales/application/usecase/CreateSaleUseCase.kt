package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.`in`.CreateSaleCommand
import com.brainycorp.tourism.sales.application.port.out.CreateSaleRepository
import org.springframework.stereotype.Component


@Component
class CreateSaleUseCase(
    val createSaleRepository: CreateSaleRepository
): CreateSaleCommand {

    override fun execute(sale: SaleRequest) {
        createSaleRepository.execute(sale)
    }
}