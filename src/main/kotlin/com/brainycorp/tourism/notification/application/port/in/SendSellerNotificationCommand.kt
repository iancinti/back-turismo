package com.brainycorp.tourism.notification.application.port.`in`

interface SendSellerNotificationCommand {

    fun execute(email: String, pass: String)
}