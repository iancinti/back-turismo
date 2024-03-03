package com.brainycorp.tourism.domain

data class Sale(
    val numSale: Int,
    val paymentMethod: String,
    val client: Client,
    val packagee: Package,
    val service: Service
)
