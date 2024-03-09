package com.brainycorp.tourism.sales.adapter.`in`.controller

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.`in`.*
import com.brainycorp.tourism.sales.domain.Sale
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SalesControllerAdapter (
    val searchSalesQuery: SearchSalesQuery,
    val retriveSaleByIdQuery: RetriveSaleByIdQuery,
    val createSaleCommand: CreateSaleCommand,
    val updateSaleCommand: UpdateSaleCommand,
    val deleteSaleCommand: DeleteSaleCommand
) {

    val log = LoggerFactory.getLogger("SaleControllerAdapter")


    @GetMapping
    @CrossOrigin("*")
    fun searchSales(@RequestParam searcher: String): ResponseEntity<List<Sale>>{
        log.info("Buscando ventas por searcher: $searcher")
        val response =searchSalesQuery.execute(searcher)
        log.info("Se encontraron las ventas: $response")
        return  ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun retriveSaleById(@PathVariable("id") id: Int): ResponseEntity<Sale> {
        log.info("Buscando venta por ID: $id")
        val response = retriveSaleByIdQuery.execute(id)
        log.info("Se encontro la venta: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun createSale(@RequestBody sale: SaleRequest): ResponseEntity<Void> {
        log.info("Creando la venta: $sale")
        createSaleCommand.execute(sale)
        log.info("Se creo la venta: $sale")
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PatchMapping("/{id}")
    fun updateSale(@RequestBody sale: SaleRequest, @PathVariable("id") id:String): ResponseEntity<Void> {
        log.info("Modificando la venta con ID: $id")
        updateSaleCommand.execute(sale, id)
        log.info("Se modifico la venta con ID: $id")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

   @DeleteMapping("/{id}")
    fun deleteSale(@PathVariable("id") id: String): ResponseEntity<Void> {
       log.info("Eliminando la venta con ID: $id")
       deleteSaleCommand.execute(id)
       log.info("Se elimino la venta con ID: $id")
       return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}