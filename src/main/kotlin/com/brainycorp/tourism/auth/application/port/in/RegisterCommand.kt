package com.brainycorp.tourism.auth.application.port.`in`

import com.brainycorp.tourism.auth.adapter.`in`.controller.model.AuthResponse
import com.brainycorp.tourism.auth.domain.User

interface RegisterCommand {
    fun execute(user: User)
}