package com.brainycorp.tourism.sales.domain

import com.brainycorp.tourism.client.domain.Client
import com.brainycorp.tourism.service.domain.Service
import com.brainycorp.tourism.packagee.domain.Package

data class Sale(
    val numSale: Int?,
    val paymentMethod: String?,
    val client: Client?,
    val packagee: Package?,
    val service: Service?
)
