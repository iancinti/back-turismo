package com.brainycorp.tourism.seller.application.port.out

interface DeleteSellerRepository {

    fun execute(id: String)
}