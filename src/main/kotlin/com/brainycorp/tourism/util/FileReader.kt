package com.brainycorp.tourism.util

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class FileReader {

    companion object {

        val INSERT_SERVICE: String = getSql("insertService")
        val INSERT_PACKAGE: String = getSql("insertPackage")


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