package com.brainycorp.tourism.client.application.port.`in`

interface DeleteClientCommand {
    fun execute(id: String)
}