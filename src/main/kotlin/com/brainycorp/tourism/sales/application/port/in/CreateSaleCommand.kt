package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.domain.Sale

interface CreateSaleCommand {

    fun execute(sale: Sale)

}