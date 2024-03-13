package com.brainycorp.tourism.shared.email.application.usecase

import com.brainycorp.tourism.shared.email.application.port.out.SendGmailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class SendGmailUseCase : SendGmailRepository {

    @Autowired
    private lateinit var emailSender: JavaMailSender

    override fun sendSimpleMessage(to: String, subject: String, text: String?) {
        val message = SimpleMailMessage()
        message.setFrom("")
        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)
        emailSender.send(message)
    }
}

