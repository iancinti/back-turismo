package com.brainycorp.tourism.notification.domain

data class Payment(
    val to: String,
    val totalPrice: String,
    val discount: String,
    val services: List<ServiceRequest>
)
data class ServiceRequest(
    val codigo: String,
    val nombre: String,
    val destino: String,
    val costo: String
)
