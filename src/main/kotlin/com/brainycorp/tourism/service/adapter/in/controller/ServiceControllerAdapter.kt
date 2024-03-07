package com.brainycorp.tourism.service.adapter.`in`.controller

import com.brainycorp.tourism.service.application.port.`in`.*
import com.brainycorp.tourism.service.domain.Service
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/services")
class ServiceControllerAdapter(
    val searchServicesQuery: SearchServicesQuery,
    val createServiceCommand: CreateServiceCommand,
    val retriveServiceByIdQuery: RetriveServiceByIdQuery,
    val retriveServiceByTypeIdQuery: RetriveServiceByTypeIdQuery,
    val updateServiceCommand: UpdateServiceCommand,
    val deleteServiceCommand: DeleteServiceCommand
) {


    @GetMapping
    @CrossOrigin("*")
    fun retriveServiceBySearch(@RequestParam("searcher") searchInput: String): ResponseEntity<List<Service>> {
        return ResponseEntity(searchServicesQuery.execute(searchInput), HttpStatus.OK)
    }

    @GetMapping("/{code}")
    fun retriveServiceById(@PathVariable("code") code: Int): ResponseEntity<Service> {
        val response = retriveServiceByIdQuery.execute(code)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/type/{type_id}")
    fun retriveServiceByTypeId(@PathVariable("type_id") typeId: Int): ResponseEntity<List<Service>> {
        val response = retriveServiceByTypeIdQuery.execute(typeId)
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    fun createService(@RequestBody service: Service): ResponseEntity<Void> {
        createServiceCommand.execute(service)
        return ResponseEntity(HttpStatus.CREATED)
    }


    @PatchMapping("/{code}")
    fun updateService(@RequestBody service: Service, @PathVariable("code") code: String): ResponseEntity<Void> {
        updateServiceCommand.execute(service, code)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @DeleteMapping("/{code}")
    fun deleteService(@PathVariable("code") code: String): ResponseEntity<Void> {
        deleteServiceCommand.execute(code)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}