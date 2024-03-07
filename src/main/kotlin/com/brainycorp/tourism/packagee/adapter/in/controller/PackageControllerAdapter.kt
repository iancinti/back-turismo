package com.brainycorp.tourism.packagee.adapter.`in`.controller

import com.brainycorp.tourism.packagee.application.port.`in`.*
import com.brainycorp.tourism.packagee.domain.Package
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

    @GetMapping
    @CrossOrigin("*")
    fun retrivePackageBySearch(@RequestParam("searcher") searchInput: String): ResponseEntity<List<Package>> {
        return ResponseEntity(packagesByCriteriaQuery.execute(searchInput), HttpStatus.OK)
    }


    @GetMapping("/{code}")
    fun retrivePackageByCode(@PathVariable("code") code: String): ResponseEntity<Package> {
        val response = retrivePackageByCodeQuery.execute(code)
        return ResponseEntity(response, HttpStatus.OK)
    }


   @PostMapping("/create")
    fun createPackage(@RequestBody packag: Package): ResponseEntity<Void> {
        createPackageCommand.execute(packag)
        return ResponseEntity(HttpStatus.CREATED)

    }

    @PatchMapping("/{code}")
    fun updatePackage(@RequestBody packag: Package, @PathVariable("code") code: String): ResponseEntity<Void> {
        updatePackageCommand.execute(packag, code)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{code}")
    fun deletePackage(@PathVariable("code") code: String): ResponseEntity<Void> {
        deletePackageCommand.execute(code)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}