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
        log.info("Buscando Clientes por searcher: $searcher")
        val response= searchClientQuery.execute(searcher)
        log.info("Se encontraron los clientes: $response")
        return ResponseEntity(response,HttpStatus.OK)
    }


    @GetMapping("/{id}")
    fun retriveClientById(@PathVariable("id") id: Int): ResponseEntity<Client> {
        log.info("Buscando Cliente por ID: $id")
        val response = retriveClientByIdQuery.execute(id)
        log.info("Se encontro el cliente: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @CrossOrigin("*")
    fun createClient(@RequestBody client: Client): ResponseEntity<Void> {
        log.info("Creando el cliente: $client")
        createClientCommand.execute(client)
        log.info("Se creo el cliente: $client")
        return ResponseEntity(HttpStatus.CREATED)

    }

    @PatchMapping("/{id}")
    @CrossOrigin("*")
    fun updateClient(@RequestBody client: Client, @PathVariable("id") id: String): ResponseEntity<Void> {
        log.info("Modificando el cliente con ID: $id")
        updateClientCommand.execute(client, id)
        log.info("Se modifico el cliente con ID: $id")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("*")
    fun deleteClient(@PathVariable("id") id: String): ResponseEntity<Void>{
        log.info("Eliminando el cliente con ID: $id")
        deleteClientCommand.execute(id)
        log.info("Se elimino el cliente con ID: $id")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}