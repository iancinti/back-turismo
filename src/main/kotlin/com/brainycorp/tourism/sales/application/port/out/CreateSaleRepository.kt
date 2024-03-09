package com.brainycorp.tourism.sales.application.port.out

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest

interface CreateSaleRepository {

    fun execute(sale: SaleRequest)

}