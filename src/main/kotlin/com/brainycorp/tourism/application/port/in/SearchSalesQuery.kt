package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Sale

interface SearchSalesQuery {
    fun execute(searcher: String): List<Sale>

}