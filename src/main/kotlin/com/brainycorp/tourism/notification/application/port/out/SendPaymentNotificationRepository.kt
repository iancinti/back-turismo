package com.brainycorp.tourism.notification.application.port.out

import com.brainycorp.tourism.notification.domain.Payment

interface SendPaymentNotificationRepository {
    fun execute(masaage: Payment)
}