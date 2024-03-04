package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest

interface UpdateSaleCommand {

    fun execute(sale: SaleRequest, saleId: String)
}