package com.brainycorp.tourism.packagee.domain


data class Package(
    val code: String?,
    val name: String?,
    val destination: String?,
    val cost: Double?,
    val pic: String?,
    val services: List<Item>?
)

data class Item(
    val code: Int?,
    val type: String?,
    val description: String?,
    val destination: String?,
    val date: String?,
    val cost: Double?,
    val pic: String?
)
