package com.brainycorp.tourism.client.application.port.`in`

import com.brainycorp.tourism.client.domain.Client

interface SearchClientQuery {
    fun execute(searcher: String): List<Client>
}