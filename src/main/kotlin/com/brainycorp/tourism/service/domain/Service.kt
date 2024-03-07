package com.brainycorp.tourism.service.domain

data class Service(
    val code: Int?,
    val type: String?,
    val description: String?,
    val destination: String?,
    val date: String?,
    val cost: Double?,
    val pic: String?
)
