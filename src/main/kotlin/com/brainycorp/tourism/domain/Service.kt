package com.brainycorp.tourism.domain

data class Service(
    val code: String,
    val description: String,
    val destination: String,
    val date: String?,
    val cost: Double
)
