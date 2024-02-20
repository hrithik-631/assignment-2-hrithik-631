// WeatherListAdapter.kt
package com.example.weathera2 // Use your actual package name

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.squareup.moshi.JsonDataException

import com.example.weathera2.Weather
// other imports...

class WeatherListAdapter {
    @ToJson
    fun toJson(weatherList: List<Weather>): String {
        val weather = weatherList.firstOrNull() ?: return ""
        return "${weather.main}, ${weather.description}, ${weather.icon}"
    }

    @FromJson
    fun fromJson(weatherString: String): List<Weather> {
        val parts = weatherString.split(", ")
        if (parts.size != 3) throw JsonDataException("Invalid weather data: $weatherString")

        val weather = Weather(
            id = 0, // Placeholder ID
            main = parts[0].trim(),
            description = parts[1].trim(),
            icon = parts[2].trim()
        )
        return listOf(weather)
    }
}
