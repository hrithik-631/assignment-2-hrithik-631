
package com.example.weathera2

import javax.inject.Inject
import com.example.weathera2.util.Resource
import com.example.weathera2.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log



class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {
    private var cachedWeatherResponse: WeatherResponse? = null

    // Hardcoded city and API key
    private val hardcodedCity = "Corvallis,OR,US"
    private val hardcodedApiKey = "06cbdfdbd6b5e7cc3c7a54eb9f601167"

    suspend fun getWeatherForecast(city: String, apiKey: String): WeatherResponse? = withContext(Dispatchers.IO) {
        // Using the hardcoded values instead of parameters
        if (shouldFetch(cachedWeatherResponse)) {
            val response = weatherService.getCurrentWeather(hardcodedCity, hardcodedApiKey)
            if (response.isSuccessful) {
                Log.d("WeatherRepository", "API Response: ${response.body()}")

                cachedWeatherResponse = response.body()
            }
        else {
            Log.e("WeatherRepository", "API Call failed")
            return@withContext null
        }
        }
        return@withContext cachedWeatherResponse
    }

    private fun shouldFetch(cachedData: WeatherResponse?): Boolean {
        // Example logic: fetch new data if cache is empty
        return cachedData == null
    }
}
