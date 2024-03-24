package com.brainycorp.tourism.notification.adapter.`in`.controller

import com.brainycorp.tourism.notification.application.port.`in`.SendPaymentNotificationCommand
import com.brainycorp.tourism.notification.application.port.`in`.SendSellerNotificationCommand
import com.brainycorp.tourism.notification.domain.Payment
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notification")
class NotificationControllerAdapter(
    val sendPaymentNotificationCommand: SendPaymentNotificationCommand,
    val sendSellerNotificationCommand: SendSellerNotificationCommand
) {
    val log = LoggerFactory.getLogger("NotificationControllerAdapter")
    @PostMapping("/send")
    @CrossOrigin("*")
    fun sendPaymentNotification(@RequestBody payment: Payment) {
        log.info("Enviando notificacion: $payment")
        sendPaymentNotificationCommand.execute(payment)
        log.info("Se envió la notificacion: $payment")
    }

    @PostMapping("/send/seller")
    @CrossOrigin("*")
    fun sendSellerNotification(@RequestParam email: String, @RequestParam password: String) {
        log.info("Enviando notificacion: $email")
        sendSellerNotificationCommand.execute(email, password)
        log.info("Se envió la notificacion: $email")
    }
}

