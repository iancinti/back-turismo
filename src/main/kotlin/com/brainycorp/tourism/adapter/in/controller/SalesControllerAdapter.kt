package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.CreateSaleCommand
import com.brainycorp.tourism.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.domain.Sale
import com.brainycorp.tourism.domain.Seller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sales")
class SalesControllerAdapter (
    val searchSalesQuery: SearchSalesQuery,
    val createSaleCommand: CreateSaleCommand
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


}