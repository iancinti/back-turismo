package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Seller

interface UpdateSellerRepository {

    fun execute(seller: Seller, sellerId: String)
}