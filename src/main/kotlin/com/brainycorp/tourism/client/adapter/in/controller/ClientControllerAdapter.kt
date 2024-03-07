package com.brainycorp.tourism.client.adapter.`in`.controller

import com.brainycorp.tourism.client.application.port.`in`.*
import com.brainycorp.tourism.client.domain.Client
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
class ClientControllerAdapter(
    val searchClientQuery: SearchClientQuery,
    val createClientCommand: CreateClientCommand,
    val retriveClientByIdQuery: RetriveClientByIdQuery,
    val updateClientCommand: UpdateClientCommand,
    val deleteClientCommand: DeleteClientCommand
) {

    val log = LoggerFactory.getLogger("ClientControllerAdapter")

    @GetMapping
    @CrossOrigin("*")
    fun searchClient(@RequestParam searcher: String): ResponseEntity<List<Client>>{
        log.info("Buscando Cliente por searcher: $searcher")
        val response= searchClientQuery.execute(searcher)
        log.info("Se encontraron los clientes: $response")
        return ResponseEntity(response,HttpStatus.OK)
    }


    @GetMapping("/{id}")
    fun retriveClientById(@PathVariable("id") id: Int): ResponseEntity<Client> {
        val response = retriveClientByIdQuery.execute(id)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun createClient(@RequestBody client: Client): ResponseEntity<Void> {
        createClientCommand.execute(client)
        return ResponseEntity(HttpStatus.CREATED)

    }

    @PatchMapping("/{id}")
    fun updateClient(@RequestBody client: Client, @PathVariable("id") id: String): ResponseEntity<Void> {
        updateClientCommand.execute(client, id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable("id") id: String): ResponseEntity<Void>{
        deleteClientCommand.execute(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}