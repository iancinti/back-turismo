package com.brainycorp.tourism.notification.application.usecase

import com.brainycorp.tourism.notification.application.port.`in`.SendSellerNotificationCommand
import com.brainycorp.tourism.notification.application.port.out.SendSellerNotificationRepository
import org.springframework.stereotype.Component

@Component
class SendSellerNotificationUseCase(
    val sendSellerNotificationRepository: SendSellerNotificationRepository
): SendSellerNotificationCommand {

    override fun execute(email: String, pass: String) {
        return sendSellerNotificationRepository.execute(email, pass)
    }
}