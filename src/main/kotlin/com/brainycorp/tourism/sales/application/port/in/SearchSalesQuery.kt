package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleResponse

interface SearchSalesQuery {
    fun execute(searcher: String): List<SaleResponse>

}