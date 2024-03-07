package com.brainycorp.tourism.sales.domain

data class Sale(
    val numSale: Int?,
    val paymentMethod: String?,
    val client: Buyer?,
    val packagee: PackageSold?,
    val service: ServiceSold?
)


data class PackageSold(
    val code: String?,
    val name: String?,
    val destination: String?,
    val cost: Double?,
    val pic: String?,
    val services: List<Item>?
)

data class Item(
    val code: Int?,
    val type: String?,
    val description: String?,
    val destination: String?,
    val date: String?,
    val cost: Double?,
    val pic: String?
)

data class ServiceSold(
    val code: Int?,
    val type: String?,
    val description: String?,
    val destination: String?,
    val date: String?,
    val cost: Double?,
    val pic: String?
)

data class Buyer(
    val name: String?,
    val lastname: String?,
    val email: String?
)