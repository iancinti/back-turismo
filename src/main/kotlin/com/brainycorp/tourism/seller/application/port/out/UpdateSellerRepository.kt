package com.brainycorp.tourism.seller.application.port.out

import com.brainycorp.tourism.seller.domain.Seller

interface UpdateSellerRepository {

    fun execute(seller: Seller, sellerId: String)
}