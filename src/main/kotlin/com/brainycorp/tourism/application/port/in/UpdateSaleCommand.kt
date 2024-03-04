package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.adapter.`in`.controller.model.SaleRequest

interface UpdateSaleCommand {

    fun execute(sale: SaleRequest, saleId: String)
}