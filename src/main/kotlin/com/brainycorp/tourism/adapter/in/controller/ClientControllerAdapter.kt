package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.CreateClientCommand
import com.brainycorp.tourism.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.domain.Client
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
class ClientControllerAdapter(
    val searchClientQuery: SearchClientQuery,
    val createClientCommand: CreateClientCommand
) {

    @GetMapping
    @CrossOrigin("*")
    fun searchClient(@RequestParam searcher: String): ResponseEntity<List<Client>>{
        val response= searchClientQuery.execute(searcher)
        return ResponseEntity(response,HttpStatus.OK)
    }

    @PostMapping
    fun createClient(@RequestBody client: Client): ResponseEntity<Void> {
        createClientCommand.execute(client)
        return ResponseEntity(HttpStatus.CREATED)

    }

}