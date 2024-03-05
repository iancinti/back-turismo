package com.brainycorp.tourism.packagee.application.usecase

import com.brainycorp.tourism.packagee.application.port.`in`.UpdatePackageCommand
import com.brainycorp.tourism.packagee.application.port.out.UpdatePackageRepository
import com.brainycorp.tourism.packagee.domain.Package
import org.springframework.stereotype.Component

@Component
class UpdatePackageUseCase(
    val updatePackageRepository: UpdatePackageRepository
): UpdatePackageCommand {

    override fun execute(packag: Package, packageCode: String) {
        updatePackageRepository.execute(packag, packageCode)
    }

}