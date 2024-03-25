package com.brainycorp.tourism.packagee.adapter.`in`.controller

import com.brainycorp.tourism.packagee.application.port.`in`.*
import com.brainycorp.tourism.packagee.domain.Package
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/packages")
class PackageControllerAdapter(
    val packagesByCriteriaQuery: SearchPackagesQuery,
    val retrivePackageByCodeQuery: RetrivePackageByCodeQuery,
    val createPackageCommand: CreatePackageCommand,
    val updatePackageCommand: UpdatePackageCommand,
    val deletePackageCommand: DeletePackageCommand
) {

    val log = LoggerFactory.getLogger("PackageControllerAdapter")

    @GetMapping
    @CrossOrigin("*")
    fun retrivePackageBySearch(@RequestParam("searcher") searchInput: String): ResponseEntity<List<Package>> {
        log.info("Buscando paquetes por searcher: $searchInput")
        val response = packagesByCriteriaQuery.execute(searchInput)
        log.info("Se encontraron los paquetes: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }


    @GetMapping("/{code}")
    @CrossOrigin("*")
    fun retrivePackageByCode(@PathVariable("code") code: String): ResponseEntity<Package> {
        log.info("Buscando paquete por codigo: $code")
        val response = retrivePackageByCodeQuery.execute(code)
        log.info("Se encontro el paquete: $response")
        return ResponseEntity(response, HttpStatus.OK)
    }


   @PostMapping
   @CrossOrigin("*")
    fun createPackage(@RequestBody packag: Package): ResponseEntity<Int> {
       log.info("Creando el paquete: $packag")
        val resp = createPackageCommand.execute(packag)
       log.info("Se creo el paquete: $packag")
       return ResponseEntity(resp, HttpStatus.CREATED)

    }

    @PatchMapping("/{code}")
    @CrossOrigin("*")
    fun updatePackage(@RequestBody packag: Package, @PathVariable("code") code: String): ResponseEntity<Void> {
        log.info("Modificando el paquete con codigo: $code")
        updatePackageCommand.execute(packag, code)
        log.info("Se modifico el paquete con codigo: $code")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{code}")
    @CrossOrigin("*")
    fun deletePackage(@PathVariable("code") code: String): ResponseEntity<Void> {
        log.info("Eliminando el paquete con codigo: $code")
        deletePackageCommand.execute(code)
        log.info("Se elimino el paquete con codigo: $code")
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}