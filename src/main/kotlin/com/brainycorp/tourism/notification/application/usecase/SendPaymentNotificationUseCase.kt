package com.brainycorp.tourism.notification.application.usecase

import com.brainycorp.tourism.notification.application.port.`in`.SendPaymentNotificationCommand
import com.brainycorp.tourism.notification.application.port.out.SendPaymentNotificationRepository
import com.brainycorp.tourism.notification.domain.Payment
import org.springframework.stereotype.Component

@Component
class SendPaymentNotificationUseCase(
    val sendPaymentNotificationRepository: SendPaymentNotificationRepository
) : SendPaymentNotificationCommand {

    override fun execute(masaage: Payment) {
        sendPaymentNotificationRepository.execute(masaage)
    }
}

