package com.brainycorp.tourism.packagee.adapter.out.jdbc.model

import com.brainycorp.tourism.packagee.domain.Item
import com.brainycorp.tourism.packagee.domain.Package

data class ServicePackageJdbcModel(
    val codePackage: String,
    val namePackage: String,
    val  destinationPackage: String,
    val costPackage: String,
    val codeService: String,
    val descriptionService: String,
    val dateService: String,
    val costService : String,
    val typeName: String,
    val pic: String
){

    companion object {
        fun fromDomain(jdbcModel: List<ServicePackageJdbcModel>): List<Package>{
            val packages = mutableListOf<Package>()
            jdbcModel.map {

                val services = mutableListOf<Item>()

                jdbcModel.map { ser ->
                    if (it.codePackage == ser.codePackage){
                        services.add(
                            Item(
                                ser.codeService.toInt(),
                                ser.typeName,
                                ser.descriptionService,
                                ser.destinationPackage,
                                ser.dateService,
                                ser.costPackage.toDouble(),
                                ""
                            )
                        )
                    }
                }
                packages.add(
                    Package(
                        it.codePackage,
                        it.namePackage,
                        it.destinationPackage,
                        it.costPackage.toDouble(),
                        it.pic,
                        services,
                        null
                    )
                )
            }
            return packages
        }
    }
}