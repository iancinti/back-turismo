package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.SearchClientQuery
import com.brainycorp.tourism.domain.Client
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clients")
class ClientControllerAdapter(
    val searchClientQuery: SearchClientQuery,

) {

    @GetMapping
    fun searchClient(@RequestParam searcher: String): ResponseEntity<List<Client>>{
        val response= searchClientQuery.execute(searcher)
        return ResponseEntity(response,HttpStatus.OK)
    }

}