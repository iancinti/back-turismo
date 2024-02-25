package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.`in`.SearchPackagesByCriteriaQuery
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/packages")
class PackageControllerAdapter(
    val packagesByCriteriaQuery: SearchPackagesByCriteriaQuery
) {

    @GetMapping
    @CrossOrigin("*")
    fun retrivePackageByCliteria(@RequestParam("search") searchInput: String): ResponseEntity<List<Package>> {
        return ResponseEntity(packagesByCriteriaQuery.execute(searchInput), HttpStatus.OK)
    }
}