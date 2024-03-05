package com.brainycorp.tourism.packagee.application.usecase

import com.brainycorp.tourism.packagee.application.port.`in`.DeletePackageCommand
import com.brainycorp.tourism.packagee.application.port.out.DeletePackageRepository
import org.springframework.stereotype.Component

@Component
class DeletePackageUseCase(
    val deletePackageRepository: DeletePackageRepository
): DeletePackageCommand {

    override fun execute(code: String) {
        deletePackageRepository.execute(code)
    }
}