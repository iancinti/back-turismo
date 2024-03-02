package com.brainycorp.tourism.adapter.out.jdbc.model

import com.brainycorp.tourism.domain.Package
import com.brainycorp.tourism.domain.Service

data class ServicePackageJdbcModel(
    val codePackage: String,
    val namePackage: String,
    val  destinationPackage: String,
    val costPackage: String,
    val codeService: String,
    val descriptionService: String,
    val dateService: String,
    val costService : String,
    val typeName: String
){

    companion object {
        fun fromDomain(jdbcModel: List<ServicePackageJdbcModel>): List<Package>{
            val packages = mutableListOf<Package>()
            jdbcModel.map {

                val services = mutableListOf<Service>()

                jdbcModel.map { ser ->
                    if (it.codePackage == ser.codePackage){
                        services.add(
                            Service(
                                ser.codeService,
                                ser.typeName,
                                ser.descriptionService,
                                ser.destinationPackage,
                                ser.dateService,
                                ser.costPackage.toDouble()
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
                        services
                    )
                )
            }
            return packages
        }
    }
}