package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.SearchSalesQuery
import com.brainycorp.tourism.domain.Sale
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sales")
class SalesControllerAdapter (
    val searchSalesQuery: SearchSalesQuery
) {

    @GetMapping
    fun searchSales(@RequestParam searcher: String): ResponseEntity<List<Sale>>{
        val response =searchSalesQuery.execute(searcher)
        return  ResponseEntity(response, HttpStatus.OK)

    }


}