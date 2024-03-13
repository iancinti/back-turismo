package com.brainycorp.tourism.shared.email.adapter.`in`.controller

import com.brainycorp.tourism.shared.email.application.usecase.SendGmailUseCase
import com.brainycorp.tourism.shared.email.domain.EmailResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailControllerAdapter {

    val log = LoggerFactory.getLogger("EmailControllerAdapter")

    @Autowired
    private lateinit var sendGmailUseCase: SendGmailUseCase

    @PostMapping("/send-email")
    @CrossOrigin("*")
    fun sendEmail(@RequestBody emailResponse: EmailResponse) {
        log.info("Enviando el mail: $emailResponse")
        val to = emailResponse.to
        val subject = emailResponse.subject
        val text = emailResponse.text

        if (text != null) {
            sendGmailUseCase.sendSimpleMessage(to, subject, text)
        } else {
            log.warn("El campo 'text' es null en el objeto EmailResponse recibido.")
        }

        log.info("Se envió el correo electrónico: $emailResponse")
    }
}

