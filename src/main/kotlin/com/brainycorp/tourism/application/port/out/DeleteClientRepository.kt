package com.brainycorp.tourism.application.port.out


interface DeleteClientRepository {
    fun execute(id: String)
}