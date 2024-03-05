package com.brainycorp.tourism.shared

import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DateParser {

    companion object {
        fun convertStringToDate(string: String): java.util.Date {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
            val localDate = LocalDate.parse(string, formatter)
            return Date.valueOf(localDate)
        }
    }

}