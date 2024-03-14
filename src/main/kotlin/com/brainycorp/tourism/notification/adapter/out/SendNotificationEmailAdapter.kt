package com.brainycorp.tourism.notification.adapter.out

import com.brainycorp.tourism.notification.application.port.out.SendNotificationRepository
import com.brainycorp.tourism.notification.domain.Payment
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
import org.thymeleaf.context.Context
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine

@Component
class SendNotificationEmailAdapter(
    val emailSender: JavaMailSender,
    val templateEngine: TemplateEngine,
): SendNotificationRepository {


    val log = LoggerFactory.getLogger("SendNotificationEmailAdapter")

    override fun execute(masaage: Payment) {
        try {
            val mimeMessage: MimeMessage = emailSender.createMimeMessage()
            val message = MimeMessageHelper(mimeMessage, true, "UTF-8")
            message.setTo(masaage.to)
            message.setSubject("Buen viaje!")

            val context = generateContextMessage(masaage)

            message.setText(templateEngine.process("PaymentNotification", context), true)
            emailSender.send(mimeMessage)
        }catch (error: MailException){
            log.info(error.cause?.message)
        }
    }

    fun generateContextMessage(paymentNotification: Payment): Context {
        val variablesMap = mutableMapOf<String, String>()
        variablesMap["discount"] = paymentNotification.discount ?: ""
        val context = Context()
        context.setVariables(variablesMap.toMap())
        return context
    }
}