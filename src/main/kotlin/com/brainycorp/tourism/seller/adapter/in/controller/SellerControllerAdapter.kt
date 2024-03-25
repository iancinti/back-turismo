package com.brainycorp.tourism.seller.adapter.`in`.controller

import com.brainycorp.tourism.seller.application.port.`in`.*
import com.brainycorp.tourism.seller.domain.Seller
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sellers")
@CrossOrigin(origins = ["http://localhost:3000"])
class SellerControllerAdapter(
    val searchSellerQuery: SearchSellerQuery,
    val retriveSellerByIdQuery: RetriveSellerByIdQuery,
    val createSellerCommand: CreateSellerCommand,
    val updateSellerCommand: UpdateSellerCommand,
    val deleteSellerCommand: DeleteSellerCommand
) {

    val log = LoggerFactory.getLogger("SellerControllerAdapter")

    @GetMapping
    @CrossOrigin("*")
    fun searchSeller(@RequestParam searcher: String): ResponseEntity<List<Seller>>{
        log.info("Buscando vendedores por searcher: $searcher")
        val response= searchSellerQuery.execute(searcher)
        log.info("Se encontraron los vendedores: $response")
        return ResponseEntity(response, HttpStatus.CREATED)

    }

    @GetMapping("/{email}")
    @CrossOrigin("*")
    fun retriveSellerById(@PathVariable("email") email: String): ResponseEntity<Seller> {
        log.info("Buscando vendedor por ID: $email")
        val response = retriveSellerByIdQuery.execute(email)
        log.info("Se encontro el vendedor: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @CrossOrigin("*")
    fun createSeller(@RequestBody seller: Seller): ResponseEntity<Void> {
        log.info("Creando el vendedor: $seller")
        createSellerCommand.execute(seller)
        log.info("Se creo el vendedor: $seller")
        return ResponseEntity(HttpStatus.CREATED)
    }


    @PatchMapping("/{id}")
    @CrossOrigin("*")
    fun updateSeller(@RequestBody seller: Seller, @PathVariable("id") id:String): ResponseEntity<Void> {
        log.info("Modificando el vendedor con ID: $id")
        updateSellerCommand.execute(seller, id)
        log.info("Se modifico el vendedor con ID: $id")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @DeleteMapping("/{id}")
    @CrossOrigin("*")
    fun deleteSeller(@PathVariable("id") id:String): ResponseEntity<Void> {
        log.info("Eliminando el vendedor con ID: $id")
        deleteSellerCommand.execute(id)
        log.info("Se elimino el vendedor con ID: $id")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}