package com.brainycorp.tourism.notification.adapter.`in`.controller

import com.brainycorp.tourism.notification.application.port.`in`.SendPaymentNotificationCommand
import com.brainycorp.tourism.notification.domain.Payment
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notification")
class NotificationControllerAdapter(
    val sendPaymentNotificationCommand: SendPaymentNotificationCommand
) {
    val log = LoggerFactory.getLogger("NotificationControllerAdapter")
    @PostMapping("/send")
    @CrossOrigin("*")
    fun sendPaymentNotification(@RequestBody payment: Payment) {
        log.info("Enviando notificacion: $payment")
        sendPaymentNotificationCommand.execute(payment)
        log.info("Se envió la notificacion: $payment")
    }
}

