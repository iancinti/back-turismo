package com.brainycorp.tourism.application.port.`in`

interface DeleteClientCommand {
    fun execute(id: String)
}