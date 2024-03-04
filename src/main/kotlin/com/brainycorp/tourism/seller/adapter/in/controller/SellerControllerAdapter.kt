package com.brainycorp.tourism.seller.adapter.`in`.controller

import com.brainycorp.tourism.seller.application.port.`in`.CreateSellerCommand
import com.brainycorp.tourism.seller.application.port.`in`.DeleteSellerCommand
import com.brainycorp.tourism.seller.application.port.`in`.SearchSellerQuery
import com.brainycorp.tourism.seller.application.port.`in`.UpdateSellerCommand
import com.brainycorp.tourism.domain.Seller
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
class SellerControllerAdapter(
    val searchSellerQuery: SearchSellerQuery,
    val createSellerCommand: CreateSellerCommand,
    val updateSellerCommand: UpdateSellerCommand,
    val deleteSellerCommand: DeleteSellerCommand
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


    @PatchMapping("/{id}")
    fun updateSeller(@RequestBody seller: Seller,@PathVariable("id") id:String): ResponseEntity<Void> {
        updateSellerCommand.execute(seller, id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @DeleteMapping("/{id}")
    fun deleteSeller(@PathVariable("id") id:String): ResponseEntity<Void> {
        deleteSellerCommand.execute(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}