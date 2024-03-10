package com.brainycorp.tourism.sales.adapter.`in`.controller.model

data class SaleResponse(
    val numSale: Int?,
    val paymentMethod: String?,
    val client: Client?,
    val cost: Double
)

data class Client(
    val name: String?,
    val lastname: String?,
    val email: String?
)