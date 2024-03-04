package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.domain.Sale

interface SearchSalesQuery {
    fun execute(searcher: String): List<Sale>

}