package com.brainycorp.tourism.application.port.out

import com.brainycorp.tourism.domain.Sale

interface CreateSaleRepository {

    fun execute(sale: Sale)

}