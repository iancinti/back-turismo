package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.sales.domain.Sale

interface RetriveSaleByIdQuery {

    fun execute(id: Int): Sale
}