package com.brainycorp.tourism.shared

import org.springframework.core.io.ClassPathResource
import java.io.IOException

class FileReader {

    companion object {
        fun getSql(fileName: String): String {
            val pathResource = "static/querys/$fileName.sql"
            try {
                val resource = ClassPathResource(pathResource)
                return resource.inputStream.bufferedReader().use { it.readText() }
            } catch (e: IOException) {
                throw RuntimeException("Error al leer el archivo SQL: $fileName", e)
            }
        }
    }
}