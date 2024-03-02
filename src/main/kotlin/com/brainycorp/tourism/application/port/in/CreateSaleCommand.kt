package com.brainycorp.tourism.application.port.`in`

import com.brainycorp.tourism.domain.Sale

interface CreateSaleCommand {

    fun execute(sale: Sale)

}