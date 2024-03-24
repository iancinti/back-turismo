package com.brainycorp.tourism.notification.adapter.out.smtp

import com.brainycorp.tourism.notification.application.port.out.SendSellerNotificationRepository
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class SendSellerNotificationEmailAdapter(
    val emailSender: JavaMailSender,
): SendSellerNotificationRepository {

    val log = LoggerFactory.getLogger("SendSellerNotificationEmailAdapter")

    override fun execute(email: String, pass: String) {
        try {
            val mimeMessage: MimeMessage = emailSender.createMimeMessage()
            val message = MimeMessageHelper(mimeMessage, true, "UTF-8")
            message.setTo(email)
            message.setSubject("Bienvenido al equipo TuristeAr!")

            message.setText("Muchas gracios por formar parte de TuristeAr, para iniciar secion use su email con la contraseña: $pass")
            emailSender.send(mimeMessage)
        }catch (error: MailException){
            log.info(error.cause?.message)
        }
    }

}