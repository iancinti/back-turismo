package com.brainycorp.tourism.domain

data class Package(
    val code: String?,
    val name: String,
    val destination: String,
    val cost: Double,
    val pic: String,
    val services: List<Service>
)
