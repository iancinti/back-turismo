package com.brainycorp.tourism.seller.application.port.`in`

import com.brainycorp.tourism.domain.Seller

interface SearchSellerQuery {

    fun execute(searcher: String): List<Seller>
}