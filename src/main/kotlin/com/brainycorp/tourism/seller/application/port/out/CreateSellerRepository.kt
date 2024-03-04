package com.brainycorp.tourism.seller.application.port.out

import com.brainycorp.tourism.domain.Seller

interface CreateSellerRepository {

    fun execute(seller: Seller)

}