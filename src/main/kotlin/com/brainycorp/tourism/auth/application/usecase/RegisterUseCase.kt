package com.brainycorp.tourism.auth.application.usecase

import com.brainycorp.tourism.auth.application.port.`in`.RegisterCommand
import com.brainycorp.tourism.auth.application.port.out.ResgisterRepository
import com.brainycorp.tourism.auth.domain.User
import org.springframework.stereotype.Component

@Component
class RegisterUseCase(
    val resgisterRepository: ResgisterRepository
): RegisterCommand {

    override fun execute(user: User) {
        resgisterRepository.execute(user)
    }
}