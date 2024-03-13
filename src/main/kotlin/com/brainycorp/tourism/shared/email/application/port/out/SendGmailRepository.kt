package com.brainycorp.tourism.shared.email.application.port.out

interface SendGmailRepository {
    fun sendSimpleMessage(to: String, subject: String, text: String?)
}