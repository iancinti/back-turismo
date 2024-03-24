package com.brainycorp.tourism.auth.adapter.`in`.controller.model

data class AuthResponse(
    val user: String,
    val authorities: List<String>
)
