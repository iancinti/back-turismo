package com.brainycorp.tourism.adapter.`in`.controller

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.application.port.`in`.SearchPackagesByCriteriaQuery
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
    fun retrivePackageByCliteria(@RequestParam("search") searchInput: String): List<Package> {
        return packagesByCriteriaQuery.execute(searchInput)
    }
}