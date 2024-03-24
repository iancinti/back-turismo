package com.brainycorp.tourism.notification.application.port.out

interface SendSellerNotificationRepository {

    fun execute(email: String, pass: String)
}