package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.application.port.`in`.SearchServicesQuery
import com.brainycorp.tourism.domain.Service
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services")
class ServiceControllerAdapter(
    val searchServicesQuery: SearchServicesQuery
) {

    @GetMapping
    @CrossOrigin("*")
    fun retriveServiceBySearch(@RequestParam("search") searchInput: String): ResponseEntity<List<Service>> {
        return ResponseEntity(searchServicesQuery.execute(searchInput) ,HttpStatus.OK)
    }
}