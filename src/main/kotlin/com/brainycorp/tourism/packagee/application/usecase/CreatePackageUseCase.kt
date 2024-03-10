package com.brainycorp.tourism.packagee.application.usecase

import com.brainycorp.tourism.packagee.application.port.`in`.CreatePackageCommand
import com.brainycorp.tourism.packagee.application.port.out.CreatePackageRepository
import com.brainycorp.tourism.packagee.domain.Package
import org.springframework.stereotype.Component

@Component
class CreatePackageUseCase(
    val createPackageRepository: CreatePackageRepository
): CreatePackageCommand {

    override fun execute(packag: Package): Int {
        return createPackageRepository.execute(packag)
    }
}