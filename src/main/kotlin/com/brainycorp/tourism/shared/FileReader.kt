package com.brainycorp.tourism.shared

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class FileReader {

    companion object {

        val INSERT_SERVICE: String = getSql("insertService")
        val INSERT_SERVICE_PACKAGE: String = getSql("insertServicePackage")
        val INSERT_PACKAGE: String = getSql("insertPackage")
        val INSERT_PERSONAL_DATA: String = getSql("insertPersonalData")
        val INSERT_SALE: String = getSql("insertSale")

        val UPDATE_SERVICE: String = getSql("updateService")
        val UPDATE_PACKAGE: String = getSql("updatePackage")
        val UPDATE_SALE: String = getSql("updateSale")
        val UPDATE_SELLER: String = getSql("updateSeller")
        val UPDATE_CLIENT: String = getSql("updateClient")

        val DELETE_SERVICE: String = getSql("deleteService")
        val DELETE_PACKAGE: String = getSql("deletePackage")
        val DELETE_SALE: String = getSql("deleteSale")
        val DELETE_SELLER: String = getSql("deleteSeller")
        val DELETE_CLIENT: String = getSql("deleteClient")

        private fun getSql(fileName: String): String {
            val pathResource = "src/main/resources/static/querys/"
            val file = "$fileName.sql"
            try {
                return Files.readString(Paths.get(pathResource + file))
            } catch (e: IOException) {
                throw RuntimeException()
            }
        }
    }
}