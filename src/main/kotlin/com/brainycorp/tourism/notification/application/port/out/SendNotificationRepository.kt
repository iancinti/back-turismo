package com.brainycorp.tourism.notification.application.port.out

import com.brainycorp.tourism.notification.domain.Payment

interface SendNotificationRepository {
    fun execute(masaage: Payment)
}