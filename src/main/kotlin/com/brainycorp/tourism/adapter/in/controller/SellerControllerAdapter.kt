package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.CreateSellerCommand
import com.brainycorp.tourism.application.port.`in`.SearchSellerQuery
import com.brainycorp.tourism.domain.Seller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sellers")
class SellerControllerAdapter(
    val searchSellerQuery: SearchSellerQuery,
    val createSellerCommand: CreateSellerCommand
) {


    @GetMapping
    @CrossOrigin("*")
    fun searchSeller(@RequestParam searcher: String): ResponseEntity<List<Seller>>{
        val response= searchSellerQuery.execute(searcher)
        return ResponseEntity(response, HttpStatus.CREATED)

    }

    @PostMapping("/create")
    fun createSeller(@RequestBody seller: Seller): ResponseEntity<Void> {
        createSellerCommand.execute(seller)
        return ResponseEntity(HttpStatus.CREATED)
    }

}