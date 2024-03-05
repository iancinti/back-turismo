package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.seller.domain.Seller

interface SearchSellerQuery {

    fun execute(searcher: String): List<Seller>
}