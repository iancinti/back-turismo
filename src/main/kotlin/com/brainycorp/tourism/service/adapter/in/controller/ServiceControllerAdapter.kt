package com.brainycorp.tourism.service.adapter.`in`.controller

import com.brainycorp.tourism.service.application.port.`in`.*
import com.brainycorp.tourism.service.domain.Service
import org.slf4j.LoggerFactory
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

    val log = LoggerFactory.getLogger("ServiceControllerAdapter")


    @GetMapping
    @CrossOrigin("*")
    fun retriveServiceBySearch(
        @RequestParam("searcher") searchInput: String,
        @RequestParam(name = "typeId", required = false) typeId: String?
    ): ResponseEntity<List<Service>> {
        return ResponseEntity(searchServicesQuery.execute(searchInput, typeId), HttpStatus.OK)
    }

    @GetMapping("/{code}")
    @CrossOrigin("*")
    fun retriveServiceById(@PathVariable("code") code: Int): ResponseEntity<Service> {
        log.info("Buscando servicio por codigo: $code")
        val response = retriveServiceByIdQuery.execute(code)
        log.info("Se encontro el servicio: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/type/{type_id}")
    @CrossOrigin("*")
    fun retriveServiceByTypeId(@PathVariable("type_id") typeId: Int): ResponseEntity<List<Service>> {
        log.info("Buscando servicio por tipo Id: $typeId")
        val response = retriveServiceByTypeIdQuery.execute(typeId)
        log.info("Se encontaron los servicio: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    @CrossOrigin("*")
    fun createService(@RequestBody service: Service): ResponseEntity<Void> {
        log.info("Creando el servicio: $service")
        createServiceCommand.execute(service)
        log.info("Se creo el servicio: $service")
        return ResponseEntity(HttpStatus.CREATED)
    }


    @PatchMapping("/{code}")
    @CrossOrigin("*")
    fun updateService(@RequestBody service: Service, @PathVariable("code") code: String): ResponseEntity<Void> {
        log.info("Modificando el servicio con codigo: $code")
        updateServiceCommand.execute(service, code)
        log.info("Se modifico el servicio con codigo: $code")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


    @DeleteMapping("/{code}")
    @CrossOrigin("*")
    fun deleteService(@PathVariable("code") code: String): ResponseEntity<Void> {
        log.info("Eliminando el servicio con codigo: $code")
        deleteServiceCommand.execute(code)
        log.info("Se elimino el servicio con codigo: $code")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}