package com.hernandazevedo.composemovies

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun convertDateToFormattedString(releaseDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE dd 'of' MMMM 'of' yyyy", Locale("en", "US"))

        val date = inputFormat.parse(releaseDate)
        val calendar = Calendar.getInstance()
        calendar.time = date

        return outputFormat.format(calendar.time).replaceFirstChar {
            it.uppercase()
        }
    }catch (e: Exception){
        releaseDate
    }

}