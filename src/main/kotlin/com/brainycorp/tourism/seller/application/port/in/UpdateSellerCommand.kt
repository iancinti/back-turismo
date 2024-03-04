package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.domain.Seller

interface UpdateSellerCommand {

    fun execute(seller: Seller, sellerId: String)

}