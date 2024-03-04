package com.brainycorp.tourism.packagee.adapter.`in`.controller

import com.brainycorp.tourism.packagee.application.port.`in`.CreatePackageCommand
import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.packagee.application.port.`in`.SearchPackagesQuery
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/packages")
class PackageControllerAdapter(
    val packagesByCriteriaQuery: SearchPackagesQuery,
    val createPackageCommand: CreatePackageCommand
) {

    @GetMapping
    @CrossOrigin("*")
    fun retrivePackageBySearch(@RequestParam("searcher") searchInput: String): ResponseEntity<List<Package>> {
        return ResponseEntity(packagesByCriteriaQuery.execute(searchInput), HttpStatus.OK)
    }


   @PostMapping("/create")
    fun createPackage(@RequestBody packag: Package): ResponseEntity<Void> {
        createPackageCommand.execute(packag)
        return ResponseEntity(HttpStatus.CREATED)

    }

}