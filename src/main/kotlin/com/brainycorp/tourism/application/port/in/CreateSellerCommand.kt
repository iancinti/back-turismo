package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Seller

interface CreateSellerCommand {

    fun execute(seller: Seller)
}