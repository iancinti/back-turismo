package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.CreateSellerCommand
import com.brainycorp.tourism.application.port.out.CreateSellerRepository
import com.brainycorp.tourism.domain.Seller
import org.springframework.stereotype.Component

@Component
class CreateSellerUseCase(
    val createSellerRepository: CreateSellerRepository
): CreateSellerCommand {

    override fun execute(seller: Seller) {
        createSellerRepository.execute(seller)
    }
}