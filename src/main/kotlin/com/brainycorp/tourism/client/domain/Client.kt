package com.brainycorp.tourism.client.domain

data class Client(
    val id: String?,
    val name: String?,
    val lastname: String?,
    val dni: String?,
    val birthday: String?,
    val nationality: String?,
    val cellPhone: String?,
    val email: String?
)