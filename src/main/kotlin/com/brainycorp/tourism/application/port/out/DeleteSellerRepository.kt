package com.brainycorp.tourism.application.port.out

interface DeleteSellerRepository {

    fun execute(id: String)
}