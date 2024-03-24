package com.brainycorp.tourism.auth.application.usecase

import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthRequest
import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthResponse
import com.brainycorp.tourism.auth.application.port.`in`.LoginCommand
import org.springframework.stereotype.Component

@Component
class LoginUseCase(

): LoginCommand {

    override fun execute(user: AuthRequest): AuthResponse {
        return AuthResponse("")
    }
}