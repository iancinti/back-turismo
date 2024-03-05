package com.brainycorp.tourism.packagee.domain

import com.brainycorp.tourism.service.domain.Service

data class Package(
    val code: String?,
    val name: String?,
    val destination: String?,
    val cost: Double?,
    val pic: String?,
    val services: List<Service>?
)
