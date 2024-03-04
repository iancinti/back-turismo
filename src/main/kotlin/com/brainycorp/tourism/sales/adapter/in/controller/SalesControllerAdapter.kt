package com.brainycorp.tourism.sales.adapter.`in`.controller

import com.brainycorp.tourism.sales.adapter.`in`.controller.model.SaleRequest
import com.brainycorp.tourism.sales.application.port.`in`.CreateSaleCommand
import com.brainycorp.tourism.sales.application.port.`in`.DeleteSaleCommand
import com.brainycorp.tourism.sales.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.sales.application.port.`in`.UpdateSaleCommand
import com.brainycorp.tourism.domain.Sale
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SalesControllerAdapter (
    val searchSalesQuery: SearchSalesQuery,
    val createSaleCommand: CreateSaleCommand,
    val updateSaleCommand: UpdateSaleCommand,
    val deleteSaleCommand: DeleteSaleCommand
) {

    @GetMapping
    @CrossOrigin("*")
    fun searchSales(@RequestParam searcher: String): ResponseEntity<List<Sale>>{
        val response =searchSalesQuery.execute(searcher)
        return  ResponseEntity(response, HttpStatus.OK)

    }


    @PostMapping("/create")
    fun createSale(@RequestBody sale: Sale): ResponseEntity<Void> {
        createSaleCommand.execute(sale)
        return ResponseEntity(HttpStatus.CREATED)

    }


    @PatchMapping("/{id}")
    fun updateSale(@RequestBody sale: SaleRequest, @PathVariable("id") id:String): ResponseEntity<Void> {
        updateSaleCommand.execute(sale, id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


   @DeleteMapping("/{id}")
    fun deleteSale(@PathVariable("id") id: String): ResponseEntity<Void> {
        deleteSaleCommand.execute(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}