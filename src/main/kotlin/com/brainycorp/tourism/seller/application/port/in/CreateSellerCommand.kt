package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.seller.domain.Seller

interface CreateSellerCommand {

    fun execute(seller: Seller)
}