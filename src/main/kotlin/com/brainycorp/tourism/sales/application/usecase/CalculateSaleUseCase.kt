package com.brainycorp.tourism.sales.application.usecase

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.CalculateRequest
import com.brainycorp.tourism.sales.adapter.`in`.controller.model.CalculateResponse
import com.brainycorp.tourism.sales.application.port.`in`.CalculateSaleQuery
import org.springframework.stereotype.Component

@Component
class CalculateSaleUseCase: CalculateSaleQuery {
    override fun caculate(calculate: CalculateRequest): CalculateResponse {

        val totalSinDescuento = calculate.services.sumOf { it.price.toDouble() }

        val descuentoAplicable = if (calculate.services.size > 1) 0.10 else 0.0

        val totalConDescuento = totalSinDescuento * (1 - descuentoAplicable)

        val discount = if (descuentoAplicable > 0) "${descuentoAplicable * 100}%" else "0%"

        return CalculateResponse(
            calculate.services.size.toString(),
            discount,
            totalConDescuento
        )
    }
}