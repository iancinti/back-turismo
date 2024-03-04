package com.brainycorp.tourism.application.port.out


interface DeleteSaleRepository {

    fun execute(id: String)
}