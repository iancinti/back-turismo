package com.brainycorp.tourism.shared.email.domain

data class EmailResponse(val to: String, val subject: String, val text: String?)
