package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.UpdateSellerCommand
import com.brainycorp.tourism.application.port.out.UpdateSellerRepository
import com.brainycorp.tourism.domain.Seller
import org.springframework.stereotype.Component


@Component
class UpdateSellerUseCase(
    val updateSellerRepository: UpdateSellerRepository
): UpdateSellerCommand {

    override fun execute(seller: Seller, sellerId: String) {
        updateSellerRepository.execute(seller, sellerId)
    }

}