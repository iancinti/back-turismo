package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.adapter.`in`.controller.model.SaleRequest

interface UpdateSaleRepository {

    fun execute(sale: SaleRequest, saleId: String)
}