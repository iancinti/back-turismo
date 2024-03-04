package com.brainycorp.tourism.client.application.port.out


interface DeleteClientRepository {
    fun execute(id: String)
}