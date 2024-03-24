package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.seller.domain.Seller

interface RetriveSellerByIdQuery {

    fun execute (email: String): Seller
}