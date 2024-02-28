package com.brainycorp.tourism.application.usecase

import com.brainycorp.tourism.application.port.`in`.CreatePackageCommand
import com.brainycorp.tourism.application.port.out.CreatePackageRepository
import com.brainycorp.tourism.domain.Package
import org.springframework.stereotype.Component

@Component
class CreatePackageUseCase(
    val createPackageRepository: CreatePackageRepository
): CreatePackageCommand {

    override fun execute(packag: Package) {
        createPackageRepository.execute(packag)
    }
}