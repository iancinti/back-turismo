package com.brainycorp.tourism.auth.application.port.`in`

import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthRequest
import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthResponse

interface LoginCommand {
    fun execute(user: AuthRequest) : AuthResponse
}