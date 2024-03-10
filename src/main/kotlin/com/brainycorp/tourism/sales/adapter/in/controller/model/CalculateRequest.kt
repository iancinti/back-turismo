package com.brainycorp.tourism.sales.adapter.`in`.controller.model

data class CalculateRequest(
    val services: List<CountServices>
)

data class CountServices(
    val code: String,
    val price: String
)