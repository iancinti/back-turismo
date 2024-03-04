package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.application.port.`in`.CreateSaleCommand
import com.brainycorp.tourism.sales.application.port.out.CreateSaleRepository
import com.brainycorp.tourism.domain.Sale
import org.springframework.stereotype.Component


@Component
class CreateSaleUseCase(
    val createSaleRepository: CreateSaleRepository
): CreateSaleCommand {

    override fun execute(sale: Sale) {
        createSaleRepository.execute(sale)
    }
}