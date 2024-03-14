package com.brainycorp.tourism.notification.application.usecase

import com.brainycorp.tourism.notification.application.port.`in`.SendPaymentNotificationCommand
import com.brainycorp.tourism.notification.application.port.out.SendNotificationRepository
import com.brainycorp.tourism.notification.domain.Payment
import org.springframework.stereotype.Component

@Component
class SendPaymentNotificationUseCase(
    val sendNotificationRepository: SendNotificationRepository
) : SendPaymentNotificationCommand {

    override fun execute(masaage: Payment) {
        sendNotificationRepository.execute(masaage)
    }
}

