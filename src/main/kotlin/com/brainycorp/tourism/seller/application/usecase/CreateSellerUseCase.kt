package com.brainycorp.tourism.seller.application.usecase

import com.brainycorp.tourism.seller.application.port.`in`.CreateSellerCommand
import com.brainycorp.tourism.seller.application.port.out.CreateSellerRepository
import com.brainycorp.tourism.seller.domain.Seller
import org.springframework.stereotype.Component

@Component
class CreateSellerUseCase(
    val createSellerRepository: CreateSellerRepository
): CreateSellerCommand {

    override fun execute(seller: Seller) {
        createSellerRepository.execute(seller)
    }
}