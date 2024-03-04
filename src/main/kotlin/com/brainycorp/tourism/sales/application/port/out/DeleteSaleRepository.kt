package com.brainycorp.tourism.sales.application.port.out


interface DeleteSaleRepository {

    fun execute(id: String)
}