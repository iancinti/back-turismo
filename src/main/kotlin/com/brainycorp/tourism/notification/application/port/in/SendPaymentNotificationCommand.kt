package com.brainycorp.tourism.notification.application.port.`in`

import com.brainycorp.tourism.notification.domain.Payment

interface SendPaymentNotificationCommand {

    fun execute(payment: Payment)
}