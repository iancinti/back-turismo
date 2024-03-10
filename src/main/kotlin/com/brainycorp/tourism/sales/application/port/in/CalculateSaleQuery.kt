package com.brainycorp.tourism.sales.application.port.`in`

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.CalculateRequest
import com.brainycorp.tourism.sales.adapter.`in`.controller.model.CalculateResponse

interface CalculateSaleQuery {
    fun calculate(calculate: CalculateRequest): CalculateResponse
}