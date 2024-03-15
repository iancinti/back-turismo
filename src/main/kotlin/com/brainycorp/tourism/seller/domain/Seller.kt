package com.brainycorp.tourism.seller.domain

data class Seller(
    val id: String?,
    val name: String,
    val lastname: String?,
    val dni: String?,
    val birthday: String?,
    val nationality: String?,
    val cellPhone: String?,
    val email: String?,
    val charge: String?,
    val salary: Double?
)
