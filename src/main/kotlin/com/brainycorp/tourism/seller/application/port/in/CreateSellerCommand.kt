package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.domain.Seller

interface CreateSellerCommand {

    fun execute(seller: Seller)
}