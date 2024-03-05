package com.brainycorp.tourism.seller.application.usecase

import com.brainycorp.tourism.seller.application.port.`in`.UpdateSellerCommand
import com.brainycorp.tourism.seller.application.port.out.UpdateSellerRepository
import com.brainycorp.tourism.seller.domain.Seller
import org.springframework.stereotype.Component


@Component
class UpdateSellerUseCase(
    val updateSellerRepository: UpdateSellerRepository
): UpdateSellerCommand {

    override fun execute(seller: Seller, sellerId: String) {
        updateSellerRepository.execute(seller, sellerId)
    }

}